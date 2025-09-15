# ERP Frontend (React + Vite)

**Beginner-friendly Step-by-Step (Marathi + English):**

## 0) Prerequisites
- Node.js LTS install kara (v18+).  
- Backend (Spring Boot) `http://localhost:8080` var run asel asel tari chhan.

## 1) Project unzip kara & VS Code madhe open kara
- Zip extract kara.
- Folder open kara: `erp-frontend-react/`

## 2) .env set kara
- `.env.example` copy kara -> `.env`
- `VITE_API_BASE_URL` tumcha backend base URL ne update kara (eg. `http://localhost:8080`).

## 3) Install & Run
```bash
npm install
npm run dev
```
- Browser URL: `http://localhost:5173`

## 4) Login
- Login screen var `email`/`password` bharun `Sign In` kara.
- Expected backend API (example): `POST /api/auth/login` -> response madhe `token` (JWT) & `roles` (eg. `["ADMIN","SALES_EXECUTIVE"]`).
- Token localStorage madhe save hoto; nav-bar/menus role-pramane change hotat.

## 5) Navigation (Role-based)
- Sidebar madhe **Products, Customers, Suppliers, Sales Orders, Purchase Orders, GRN, Invoices, Reports**.
- User roles nusar kahi items hide/disallow hotil.

## 6) Product Module (Example)
- List page: `/products` (GET /api/products)
- Add/Edit: `/products/new` & `/products/:id/edit`

## 7) Next Steps
- Remaining screens placeholder ahet—tumhi hach pattern follow karun CRUD full kara.
- MUI components, RHF + Yup validation vapra.
- Axios instance already set ahe (JWT Authorization header auto add).

> Notes:
> - Token JWT `exp` check ahe; expire zhala tar logout hoil.
> - Interceptor 401 -> logout & redirect to login.
