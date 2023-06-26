import React, { Component } from 'react';
import CalidadService from '../../services/CalidadService';
import swal from 'sweetalert';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../../styles/UploadData.css';
import NavbarAll from '../Navbar';

class UploadCalidad extends Component {
  constructor(props) {
    super(props);
    this.state = {
      file: null,
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
      CalidadService.uploadData(formData)
        .then(() => {
          swal('¡Archivo guardado con éxito!', '', 'success').then(() => {});
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
            <b>Cargar información sobre la calidad de la leche</b>
          </h1>
          <Form.Group controlId="formFileLg">
            <br />
            <Form.Control
              className="upload-file-upload-btn"
              type="file"
              size="lg"
              onChange={this.onFileChange}
            />
            <br />
          </Form.Group>
          <Button className="upload-file-upload-btn" onClick={this.onFileUpload}>
            Cargar información
          </Button>
        </div>
      </div>
    </div>
    );
  }
}

export default UploadCalidad;
