import React from 'react';
import AllSuppliers from './components/Proveedor/AllSuppliers';
import CreateSupplier from './components/Proveedor/CreateSupplier';
import UploadAcopio from './components/Acopio/UploadAcopio';
import UploadCalidad from './components/Calidad/UploadCalidad';
import Payment from './components/Pago/Payment';
import HomeComponent from './components/HomeComponent';
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path= "/" element={<HomeComponent />} />
            <Route path= "/crear-prov" element={<CreateSupplier />} />
            <Route path= "/proveedores" element={<AllSuppliers />} />
            <Route path= "/acopio" element={<UploadAcopio />} />
            <Route path= "/calidad" element={<UploadCalidad />} />
            <Route path= "/pago" element={<Payment />} />
          </Routes>
        </BrowserRouter>
  </div>
  );
}
export default App;