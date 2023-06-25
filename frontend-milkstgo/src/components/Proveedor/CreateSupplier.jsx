import { Component } from "react";
import ProveedorService from "../../services/ProveedorService";
import '../../styles/CreateSupplier.css'
import swal from 'sweetalert';
import Axios from "axios";

class CreateSupplier extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id_proveedor: '',
            nombre: '',
            categoria: '',
            retencion: ''
        }
        this.changeIdProveedorHandler = this.changeIdProveedorHandler.bind(this);
        this.changeNombreHandler = this.changeNombreHandler.bind(this);
        this.changeCategoriaHandler = this.changeCategoriaHandler.bind(this);
        this.changeRetencionHandler = this.changeRetencionHandler.bind(this);
        this.saveProv = this.saveProv.bind(this);
    }
    saveProv = (e) => {
        e.preventDefault();
        let proveedor = {"id_proveedor": this.state.id_proveedor,
                        "nombre": this.state.nombre,
                        "categoria": this.state.categoria,
                        "retencion": this.state.retencion};
        let axiosc ={
            headers:{
                'Access-Control-Allow-Origin': '*',
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-type, Accept",
                'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE'
            }
        }

        Axios.post("http://localhost:8080/proveedor", proveedor, axiosc).then(res => {
            swal("Proveedor registrado", "El proveedor se ha registrado exitosamente.", "success").then(() => {
                window.location.href = "/proveedores";
            });
        });
    }
    changeIdProveedorHandler= (event) => {
        this.setState({id_proveedor: event.target.value});
    }
    changeNombreHandler= (event) => {
        this.setState({nombre: event.target.value});
    }
    changeCategoriaHandler= (event) => {
        this.setState({categoria: event.target.value});
    }
    changeRetencionHandler= (event) => {
        this.setState({retencion: event.target.value});
    }
    
    render(){
        return(
            <div>
                <div  className = "mainclass" >
                    <form>
                        <h1><b>Registrar un nuevo Proveedor</b></h1>
                        <div className="formcontainer">
                        <hr/>
                        <div className="container">
                            <label><strong>Código del proveedor:</strong></label>
                            <input type="text" placeholder="Codigo" name="id_proveedor" value={this.state.id_proveedor} onChange={this.changeIdProveedorHandler}/>
                            <label><strong>Nombre: </strong></label>
                            <input  type="text"placeholder="Nombre" name = "nombre"  value={this.state.nombre} onChange={this.changeNombreHandler}/>
                            <label><strong>Categoría del proveedor</strong></label>
                            <select className="select" name="categoria" value={this.state.categoria} onChange={this.changeCategoriaHandler}>
                                <option value="">Ingresa la categoría</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                            </select>
                            <label><strong>¿El proveedor tiene retención?</strong></label>
                            <select className="select" name="categoria" value={this.state.retencion} onChange={this.changeRetencionHandler}>
                                <option value="">Ingresa la categoría</option>
                                <option value="Si">Si</option>
                                <option value="No">No</option>
                            </select>
                        </div>
                        <button className="btn2" onClick={this.saveProv}>Registrar Proveedor</button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}
export default CreateSupplier;