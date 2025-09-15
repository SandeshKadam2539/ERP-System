import Typography from '@mui/material/Typography'
import Paper from '@mui/material/Paper'

export default function SalesOrders() {
  return (
    <Paper sx={ p: 2 }>
      <Typography variant="h6">Sales Orders</Typography>
      <Typography sx={ mt: 1 }>TODO: Create sales order form with products & quantities.</Typography>
    </Paper>
  )
}
