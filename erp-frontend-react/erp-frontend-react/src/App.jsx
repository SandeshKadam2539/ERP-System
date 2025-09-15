import { Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/auth/Login'
import Dashboard from './pages/dashboard/Dashboard'
import ProductsList from './pages/products/ProductsList'
import ProductForm from './pages/products/ProductForm'
import Customers from './pages/customers/Customers'
import Suppliers from './pages/suppliers/Suppliers'
import SalesOrders from './pages/sales/SalesOrders'
import PurchaseOrders from './pages/purchase/PurchaseOrders'
import GRNForm from './pages/grn/GRNForm'
import Invoices from './pages/invoices/Invoices'
import Reports from './pages/reports/Reports'
import AppLayout from './layouts/AppLayout'
import ProtectedRoute from './routes/ProtectedRoute'
import RoleRoute from './routes/RoleRoute'
import { ROLES } from './utils/roles'

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route element={<ProtectedRoute><AppLayout /></ProtectedRoute>}>
        <Route index element={<Navigate to="/dashboard" />} />
        <Route path="/dashboard" element={<Dashboard />} />

        <Route
          path="/products"
          element={
            <RoleRoute allowed={[ROLES.ADMIN]}>
              <ProductsList />
            </RoleRoute>
          }
        />
        <Route
          path="/products/new"
          element={
            <RoleRoute allowed={[ROLES.ADMIN]}>
              <ProductForm />
            </RoleRoute>
          }
        />
        <Route
          path="/products/:id/edit"
          element={
            <RoleRoute allowed={[ROLES.ADMIN]}>
              <ProductForm />
            </RoleRoute>
          }
        />

        <Route
          path="/customers"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.SALES_EXECUTIVE]}>
              <Customers />
            </RoleRoute>
          }
        />
        <Route
          path="/suppliers"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.PURCHASE_MANAGER]}>
              <Suppliers />
            </RoleRoute>
          }
        />
        <Route
          path="/sales-orders"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.SALES_EXECUTIVE]}>
              <SalesOrders />
            </RoleRoute>
          }
        />
        <Route
          path="/purchase-orders"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.PURCHASE_MANAGER]}>
              <PurchaseOrders />
            </RoleRoute>
          }
        />
        <Route
          path="/grn"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.INVENTORY_MANAGER, ROLES.PURCHASE_MANAGER]}>
              <GRNForm />
            </RoleRoute>
          }
        />
        <Route
          path="/invoices"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.ACCOUNTANT, ROLES.SALES_EXECUTIVE]}>
              <Invoices />
            </RoleRoute>
          }
        />
        <Route
          path="/reports"
          element={
            <RoleRoute allowed={[ROLES.ADMIN, ROLES.ACCOUNTANT]}>
              <Reports />
            </RoleRoute>
          }
        />
      </Route>

      <Route path="*" element={<Navigate to="/dashboard" />} />
    </Routes>
  )
}
