import { Navigate } from 'react-router-dom'
import { useAuth } from '../state/AuthContext'

export default function RoleRoute({ children, allowed = [] }) {
  const { user } = useAuth()
  const userRoles = user?.roles || []

  const ok = allowed.length === 0 || allowed.some(r => userRoles.includes(r))
  if (!ok) return <Navigate to="/dashboard" replace />
  return children
}
