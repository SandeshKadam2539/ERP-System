import { useEffect, useState } from 'react'
import api from '../../api/axios'
import { Link as RouterLink } from 'react-router-dom'
import Table from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'
import Stack from '@mui/material/Stack'
import TextField from '@mui/material/TextField'

export default function ProductsList() {
  const [rows, setRows] = useState([])
  const [q, setQ] = useState('')

  async function load() {
    const res = await api.get('/api/products', { params: { q } })
    setRows(res.data || [])
  }

  useEffect(() => { load() }, [])

  return (
    <Paper sx={{ p: 2 }}>
      <Stack direction="row" spacing={2} sx={{ mb: 2 }}>
        <TextField size="small" label="Search" value={q} onChange={e => setQ(e.target.value)} />
        <Button variant="outlined" onClick={load}>Search</Button>
        <Button variant="contained" component={RouterLink} to="/products/new">Add Product</Button>
      </Stack>
      <TableContainer>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>SKU</TableCell>
              <TableCell>Category</TableCell>
              <TableCell align="right">Unit Price</TableCell>
              <TableCell align="right">Current Stock</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map(p => (
              <TableRow key={p.id}>
                <TableCell>{p.name}</TableCell>
                <TableCell>{p.sku}</TableCell>
                <TableCell>{p.category}</TableCell>
                <TableCell align="right">{p.unitPrice}</TableCell>
                <TableCell align="right">{p.currentStock}</TableCell>
                <TableCell>
                  <Stack direction="row" spacing={1}>
                    <Button size="small" variant="outlined" component={RouterLink} to={`/products/${p.id}/edit`}>Edit</Button>
                  </Stack>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
  )
}
