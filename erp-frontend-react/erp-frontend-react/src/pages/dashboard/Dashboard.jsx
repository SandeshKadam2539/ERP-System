import Grid from '@mui/material/Grid'
import Paper from '@mui/material/Paper'
import Typography from '@mui/material/Typography'
import { useEffect, useState } from 'react'
import api from '../../api/axios'
import { ResponsiveContainer, BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts'

export default function Dashboard() {
  const [summary, setSummary] = useState({ sales: 0, purchases: 0 })
  const [topProducts, setTopProducts] = useState([])

  useEffect(() => {
    async function load() {
      try {
        const s = await api.get('/api/dashboard/sales-summary')
        setSummary(s.data || { sales: 0, purchases: 0 })
      } catch {}

      try {
        const t = await api.get('/api/dashboard/top-products')
        setTopProducts(t.data || [])
      } catch {}
    }
    load()
  }, [])

  return (
    <Grid container spacing={2}>
      <Grid item xs={12} md={6}>
        <Paper sx={{ p: 2 }}>
          <Typography variant="h6">This Month</Typography>
          <Typography>Sales: {summary.sales}</Typography>
          <Typography>Purchases: {summary.purchases}</Typography>
        </Paper>
      </Grid>
      <Grid item xs={12} md={6}>
        <Paper sx={{ p: 2, height: 300 }}>
          <Typography variant="h6" sx={{ mb: 2 }}>Top Products</Typography>
          <ResponsiveContainer width="100%" height="100%">
            <BarChart data={topProducts}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Bar dataKey="quantity" />
            </BarChart>
          </ResponsiveContainer>
        </Paper>
      </Grid>
    </Grid>
  )
}
