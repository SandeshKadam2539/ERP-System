import React, { createContext, useContext, useEffect, useMemo, useState } from 'react'
import { setToken as saveToken, getToken, clearToken } from './authStorage'
import jwtDecode from 'jwt-decode'
import dayjs from 'dayjs'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [token, setToken] = useState(getToken())
  const [user, setUser] = useState(null)

  useEffect(() => {
    if (token) {
      try {
        const decoded = jwtDecode(token)
        const exp = decoded?.exp ? dayjs.unix(decoded.exp) : null
        if (exp && exp.isBefore(dayjs())) {
          handleLogout()
          return
        }
        setUser({
          email: decoded?.sub || decoded?.email,
          roles: decoded?.roles || decoded?.authorities || [],
        })
      } catch {
        handleLogout()
      }
    } else {
      setUser(null)
    }
  }, [token])

  const handleLogin = (newToken) => {
    saveToken(newToken)
    setToken(newToken)
  }

  const handleLogout = () => {
    clearToken()
    setToken(null)
  }

  const value = useMemo(() => ({
    token,
    user,
    login: handleLogin,
    logout: handleLogout,
    isAuthenticated: !!token
  }), [token, user])

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  return useContext(AuthContext)
}
