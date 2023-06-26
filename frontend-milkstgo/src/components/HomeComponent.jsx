import React from 'react';
import { Component } from 'react';
import NavbarComponent from './NavbarHome';
import '../styles/Home.css';

class HomeComponent extends Component {
  render() {
    return (
      <div>
        <NavbarComponent />
        <div class="home-container">
          <div class="card create-provider-card">
            <h2 class="card-title">Crear proveedor</h2>
            <p class="card-description">Crea un nuevo proveedor</p>
            <a class="card-link" href="/crear-prov">Ir</a>
          </div>
          <div class="card view-providers-card">
            <h2 class="card-title">Ver proveedores</h2>
            <p class="card-description">Accede a la lista de proveedores</p>
            <a class="card-link" href="/proveedores">Ir</a>
          </div>
          <div class="card enter-collection-card">
            <h2 class="card-title">Ingresar acopio</h2>
            <p class="card-description">Registra un nuevo acopio</p>
            <a class="card-link" href="/acopio">Ir</a>
          </div>
          <div class="card enter-quality-card">
            <h2 class="card-title">Ingresar calidad</h2>
            <p class="card-description">Ingresa datos de calidad</p>
            <a class="card-link" href="/calidad">Ir</a>
          </div>
          <div class="card view-payments-card">
            <h2 class="card-title">Ver pagos</h2>
            <p class="card-description">Visualiza los pagos realizados</p>
            <a class="card-link" href="/pago">Ir</a>
          </div>
        </div>

      </div>
    );
  }
}

export default HomeComponent;