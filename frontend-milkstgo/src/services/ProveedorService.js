import axios from "axios";
const PROV_URL = "http://localhost:8080/proveedor";

class ProveedorService {
    getProveedores() {
        return axios.get(PROV_URL);
    }

    createProveedor(proveedor) {
        return axios.post(PROV_URL, proveedor);
    }
}

export default new ProveedorService();