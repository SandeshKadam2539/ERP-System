import { Outlet, NavLink, useNavigate } from 'react-router-dom'
import { useAuth } from '../state/AuthContext'
import { ROLES } from '../utils/roles'
import AppBar from '@mui/material/AppBar'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import Drawer from '@mui/material/Drawer'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'
import ListItemText from '@mui/material/ListItemText'
import Box from '@mui/material/Box'
import Button from '@mui/material/Button'

const menu = [
  { to: '/dashboard', text: 'Dashboard', roles: ['*'] },
  { to: '/products', text: 'Products', roles: [ROLES.ADMIN] },
  { to: '/customers', text: 'Customers', roles: [ROLES.ADMIN, ROLES.SALES_EXECUTIVE] },
  { to: '/suppliers', text: 'Suppliers', roles: [ROLES.ADMIN, ROLES.PURCHASE_MANAGER] },
  { to: '/sales-orders', text: 'Sales Orders', roles: [ROLES.ADMIN, ROLES.SALES_EXECUTIVE] },
  { to: '/purchase-orders', text: 'Purchase Orders', roles: [ROLES.ADMIN, ROLES.PURCHASE_MANAGER] },
  { to: '/grn', text: 'GRN', roles: [ROLES.ADMIN, ROLES.INVENTORY_MANAGER, ROLES.PURCHASE_MANAGER] },
  { to: '/invoices', text: 'Invoices', roles: [ROLES.ADMIN, ROLES.ACCOUNTANT, ROLES.SALES_EXECUTIVE] },
  { to: '/reports', text: 'Reports', roles: [ROLES.ADMIN, ROLES.ACCOUNTANT] },
]

export default function AppLayout() {
  const { user, logout } = useAuth()
  const navigate = useNavigate()

  const filtered = menu.filter(m => m.roles.includes('*') || (user?.roles || []).some(r => m.roles.includes(r)))

  return (
    <Box sx={{ display: 'flex' }}>
      <AppBar position="fixed" sx={{ zIndex: 1400 }}>
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>ERP System</Typography>
          <Typography sx={{ mr: 2 }}>{user?.email}</Typography>
          <Button color="inherit" onClick={() => { logout(); navigate('/login') }}>Logout</Button>
        </Toolbar>
      </AppBar>

      <Drawer variant="permanent" sx={{ width: 240, [`& .MuiDrawer-paper`]: { width: 240, top: 64 } }}>
        <List>
          {filtered.map(item => (
            <ListItem key={item.to} button component={NavLink} to={item.to}>
              <ListItemText primary={item.text} />
            </ListItem>
          ))}
        </List>
      </Drawer>

      <Box component="main" sx={{ flexGrow: 1, p: 3, mt: 8, ml: '240px' }}>
        <Outlet />
      </Box>
    </Box>
  )
}
