import { Component } from "react";
import PagoService from "../../services/PagoService";
import '../../styles/AllData.css'

class Payment extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pagos: [],
        };
    }

    componentDidMount() {
        PagoService.getPagos().then((res) => {
            this.setState({ pagos: res.data });
        });
    }

    render() {
        return (
            <div>
                <div style={{ textAlign: 'center' }} class="container my-2">
                    <h1 class="h1"><b>Pagos por proveedor</b></h1>
                    <table style={{ border: '1px solid black' }} class="content-table">
                        <thead>
                            <tr>
                                <th>Código proveedor</th>
                                <th>Nombre proveedor</th>
                                <th>Fecha</th>
                                <th>Número de días de envío</th>
                                <th>Total Kls Leche</th>
                                <th>Promedio Diario Kls Leche</th>
                                <th>Variación Leche</th>
                                <th>Por Grasa</th>
                                <th>Variación Grasa</th>
                                <th>Sólidos Totales</th>
                                <th>Variación Solidos Totales</th>
                                <th>Pago por Leche</th>
                                <th>Pago por Grasa</th>
                                <th>Pago por ST</th>
                                <th>Bono Frecuencia</th>
                                <th>Dcto Var Leche</th>
                                <th>Dcto Var Grasa</th>
                                <th>Dcto Var ST</th>
                                <th>Pago Total</th>
                                <th>Retención</th>
                                <th>Monto Final</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.pagos.map(pago => (
                                <tr key={pago.id_pago}>
                                    <td>{pago.id_proveedor}</td>
                                    <td>{pago.nombreproveedor}</td>
                                    <td>{pago.fecha}</td>
                                    <td>{pago.nrodiasdeenvio}</td>
                                    <td>{pago.totalklsleche}</td>
                                    <td>{pago.promdiarioklsleche}</td>
                                    <td>{pago.variacionLeche}</td>
                                    <td>{pago.porgrasa}</td>
                                    <td>{pago.vargrasa}</td>
                                    <td>{pago.solidostotales}</td>
                                    <td>{pago.varst}</td>
                                    <td>{pago.pagoxleche}</td>
                                    <td>{pago.pagoxgrasa}</td>
                                    <td>{pago.pagoxst}</td>
                                    <td>{pago.bonofrecuencia}</td>
                                    <td>{pago.dctovarleche}</td>
                                    <td>{pago.dctovargrasa}</td>
                                    <td>{pago.dctovarst}</td>
                                    <td>{pago.pagototal}</td>
                                    <td>{pago.retencion}</td>
                                    <td>{pago.montofinal}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>

        );
    }
}
export default Payment;