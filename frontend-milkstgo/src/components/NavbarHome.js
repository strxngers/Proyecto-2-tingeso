import React from 'react';
import styles from '../styles/NavbarHome.module.css';

const NavbarComponent = () => {
  return (
    <nav className={styles.navbar}>
      <div className={`${styles.navbarTitle} ${styles.roundedButton}`}>MilkStgo</div>
    </nav>
  );
};

export default NavbarComponent;
