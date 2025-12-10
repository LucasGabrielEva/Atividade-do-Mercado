import React from 'react';
import { Link } from 'react-router-dom';
import './style.css'; 

function Header() {
  return (
    <header className="header">
      <div className="container">
        <Link to="/" className="logo">Mercado Las Vida</Link>
        <nav className="nav">
          <Link to="/cadastro" className="navLink">Cadastrar Usuário</Link>
          <Link to="/usuarios" className="navLink">Listar Usuários</Link>
          <Link to="/cadastro-produto" className="navLink">Cadastrar Produto</Link>
          <Link to="/produto" className="navLink">Listar Produto</Link>
          <Link to="/promocoes" className="botao">Promoções e Avisos</Link>

        </nav>
      </div>
    </header>
  );
}

export default Header;