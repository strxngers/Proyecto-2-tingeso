import React from 'react';
import styles from '../styles/NavbarHome.module.css';

function Navbar() {
  return (
    <div>
      <header className={styles.navbar}>
        <div className={`${styles.navbarTitle} ${styles.roundedButton}`}>
          <h1>MilkStgo</h1>
        </div>
        <div className={styles.navbarButtons}>
          <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/">Volver al menú principal</a>
          <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/acopio">Subir archivos</a>
          <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/crear-prov">Ingresar nuevo Proveedor</a>
          <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/pago">Ver pagos</a>
        </div>
      </header>
    </div>
  );
}

export default Navbar;
