import { useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import api from '../../api/axios'
import Paper from '@mui/material/Paper'
import TextField from '@mui/material/TextField'
import Button from '@mui/material/Button'
import Stack from '@mui/material/Stack'
import Typography from '@mui/material/Typography'
import Alert from '@mui/material/Alert'

const schema = yup.object({
  name: yup.string().required(),
  sku: yup.string().required(),
  category: yup.string().required(),
  unitPrice: yup.number().typeError('Unit Price must be a number').min(0).required(),
  currentStock: yup.number().typeError('Current Stock must be a number').min(0).required(),
  reorderLevel: yup.number().typeError('Reorder Level must be a number').min(0).required(),
})

export default function ProductForm() {
  const { id } = useParams()
  const navigate = useNavigate()
  const { register, handleSubmit, reset, formState: { errors, isSubmitting }, setError } = useForm({
    resolver: yupResolver(schema),
    defaultValues: {
      name: '', sku: '', category: '', unitPrice: 0, currentStock: 0, reorderLevel: 0
    }
  })

  useEffect(() => {
    async function load() {
      if (id) {
        const res = await api.get(`/api/products/${id}`)
        reset(res.data)
      }
    }
    load()
  }, [id, reset])

  const onSubmit = async (values) => {
    try {
      if (id) await api.put(`/api/products/${id}`, values)
      else await api.post('/api/products', values)
      navigate('/products')
    } catch (err) {
      setError('root', { message: err.response?.data?.message || err.message })
    }
  }

  return (
    <Paper sx={{ p: 2 }}>
      <Typography variant="h6" sx={{ mb: 2 }}>{id ? 'Edit Product' : 'Add Product'}</Typography>
      {errors.root?.message && <Alert severity="error" sx={{ mb: 2 }}>{errors.root.message}</Alert>}
      <form onSubmit={handleSubmit(onSubmit)}>
        <Stack spacing={2} maxWidth={600}>
          <TextField label="Product Name" {...register('name')} error={!!errors.name} helperText={errors.name?.message} />
          <TextField label="SKU" {...register('sku')} error={!!errors.sku} helperText={errors.sku?.message} />
          <TextField label="Category" {...register('category')} error={!!errors.category} helperText={errors.category?.message} />
          <TextField label="Unit Price" {...register('unitPrice')} error={!!errors.unitPrice} helperText={errors.unitPrice?.message} />
          <TextField label="Current Stock" {...register('currentStock')} error={!!errors.currentStock} helperText={errors.currentStock?.message} />
          <TextField label="Reorder Level" {...register('reorderLevel')} error={!!errors.reorderLevel} helperText={errors.reorderLevel?.message} />
          <Stack direction="row" spacing={2}>
            <Button type="submit" variant="contained" disabled={isSubmitting}>{isSubmitting ? 'Saving...' : 'Save'}</Button>
            <Button variant="outlined" onClick={() => navigate('/products')}>Cancel</Button>
          </Stack>
        </Stack>
      </form>
    </Paper>
  )
}
