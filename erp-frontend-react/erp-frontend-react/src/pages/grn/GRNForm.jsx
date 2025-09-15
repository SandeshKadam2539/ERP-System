import Typography from '@mui/material/Typography'
import Paper from '@mui/material/Paper'

export default function GRN() {
  return (
    <Paper sx={ p: 2 }>
      <Typography variant="h6">GRN</Typography>
      <Typography sx={ mt: 1 }>TODO: On submit, call /api/grns and update stock.</Typography>
    </Paper>
  )
}
