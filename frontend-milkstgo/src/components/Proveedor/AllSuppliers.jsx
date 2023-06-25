import { Component } from "react";
import ProveedorService from "../../services/ProveedorService";
import '../../styles/AllData.css'

class AllSuppliers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            proveedores: [],
        };
    }

    componentDidMount() {
        ProveedorService.getProveedores().then((res) => {
            this.setState({ proveedores: res.data });
        });
    }
    render() {
        return (

            <div>
                <div style={{ textAlign: 'center' }} class="container my-2">

                    <h1 class="h1"><b> Lista de proveedores</b></h1>
                    <table style={{ border: '1px solid black' }} class="content-table">
                        <thead>
                            <tr>
                                <th>Codigo del proveedor</th>
                                <th>Nombre</th>
                                <th>Categoria</th>
                                <th>Retencion</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.proveedores.map(
                                    proveedor =>
                                        <tr key={proveedor.id}>
                                            <td> {proveedor.id_proveedor} </td>
                                            <td> {proveedor.nombre} </td>
                                            <td> {proveedor.categoria} </td>
                                            <td> {proveedor.retencion} </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}
export default AllSuppliers;
