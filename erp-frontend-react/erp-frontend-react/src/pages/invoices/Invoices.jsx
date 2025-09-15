import Typography from '@mui/material/Typography'
import Paper from '@mui/material/Paper'

export default function Invoices() {
  return (
    <Paper sx={ p: 2 }>
      <Typography variant="h6">Invoices</Typography>
      <Typography sx={ mt: 1 }>TODO: Generate from approved sales orders. Add PDF view.</Typography>
    </Paper>
  )
}
