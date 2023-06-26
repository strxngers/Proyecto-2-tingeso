import React, { Component } from 'react';
import AcopioService from '../../services/AcopioService';
import swal from 'sweetalert';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../../styles/UploadData.css';
import NavbarAll from '../Navbar';

class UploadAcopio extends Component {
  constructor(props) {
    super(props);
    this.state = {
      file: null,
      redirectToCalidad: false,
    };
    this.onFileChange = this.onFileChange.bind(this);
  }

  onFileChange(event) {
    this.setState({ file: event.target.files[0] });
  }

  onFileUpload = () => {
    swal({
      title: '¿Está seguro/a de subir este archivo?',
    }).then((res) => {
      const formData = new FormData();
      formData.append('file', this.state.file);
      AcopioService.uploadData(formData)
        .then(() => {
          swal('¡Archivo guardado con éxito!', '', 'success').then(() => {
            window.location.href = '/calidad'; // Redireccionar utilizando window.location.href
          });
        })
        .catch(() => {
          swal('Error al guardar el archivo', '', 'error');
        });
    });
  };

  render() {
    return (
      <div>
        <NavbarAll />
        <div className="upload-page-contain">
          <div className="upload-data-card">
            <h1 className="upload-h1">
              <b>Cargar información de acopio</b>
            </h1>
            <Form.Group controlId="formFileLg">
              <br />
              <Form.Control className="upload-file-upload-btn" type="file" size="lg" onChange={this.onFileChange} />
              <br />
            </Form.Group>
            <Button className="upload-submit-btn" onClick={this.onFileUpload}>
              Cargar información
            </Button>
          </div>
        </div>
      </div>
    );
  }
}

export default UploadAcopio;
