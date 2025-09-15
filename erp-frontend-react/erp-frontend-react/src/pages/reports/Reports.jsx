import Typography from '@mui/material/Typography'
import Paper from '@mui/material/Paper'

export default function Reports() {
  return (
    <Paper sx={ p: 2 }>
      <Typography variant="h6">Reports</Typography>
      <Typography sx={ mt: 1 }>TODO: Charts (sales, purchases) with date filters.</Typography>
    </Paper>
  )
}
