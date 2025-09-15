import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import api from '../../api/axios'
import { useAuth } from '../../state/AuthContext'
import { useNavigate } from 'react-router-dom'
import Container from '@mui/material/Container'
import TextField from '@mui/material/TextField'
import Button from '@mui/material/Button'
import Box from '@mui/material/Box'
import Typography from '@mui/material/Typography'
import Alert from '@mui/material/Alert'

const schema = yup.object({
  email: yup.string().email().required(),
  password: yup.string().min(4).required()
})

export default function Login() {
  const { login } = useAuth()
  const navigate = useNavigate()
  const { register, handleSubmit, formState: { errors, isSubmitting }, setError } = useForm({
    resolver: yupResolver(schema)
  })

  const onSubmit = async (values) => {
    try {
      const res = await api.post('/api/auth/login', values)
      const token = res.data?.token || res.data?.accessToken || res.data?.jwt
      if (!token) throw new Error('Token not found in response')
      login(token)
      navigate('/dashboard')
    } catch (err) {
      setError('root', { message: err.response?.data?.message || err.message })
    }
  }

  return (
    <Container maxWidth="xs" sx={{ mt: 12 }}>
      <Typography variant="h5" gutterBottom>Sign In</Typography>
      {errors.root?.message && <Alert severity="error" sx={{ mb: 2 }}>{errors.root.message}</Alert>}
      <Box component="form" onSubmit={handleSubmit(onSubmit)}>
        <TextField
          label="Email"
          fullWidth
          margin="normal"
          {...register('email')}
          error={!!errors.email}
          helperText={errors.email?.message}
        />
        <TextField
          label="Password"
          type="password"
          fullWidth
          margin="normal"
          {...register('password')}
          error={!!errors.password}
          helperText={errors.password?.message}
        />
        <Button type="submit" variant="contained" fullWidth disabled={isSubmitting} sx={{ mt: 2 }}>
          {isSubmitting ? 'Signing in...' : 'Sign In'}
        </Button>
      </Box>
    </Container>
  )
}
