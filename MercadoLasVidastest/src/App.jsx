import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import Header from './components/Header';
import Footer from './components/Footer';

import HomePage from './page/HomePage';
import ListaUsuariosPage from './page/ListaUsuarioPage';
import PaginaDeCadastro from './page/PaginaDeCadastro';
import PaginaDeCadastroProduto from './page/PaginaCadastroProduto';
import PageListarProduto from './page/PageListarProduto';
import PageTelaPromocoes from './page/PageTelaPromocoes';


function App() {
  return (
    <Router>
      <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
        
        <Header />

        <main style={{ flex: 1 }}>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/cadastro" element={<PaginaDeCadastro />} />
            <Route path="/usuarios" element={<ListaUsuariosPage />} />
            <Route path='/produto' element={<PageListarProduto />} />
            <Route path="/cadastro-produto" element={<PaginaDeCadastroProduto />} />
            <Route path="/promocoes" element={<PageTelaPromocoes />} />
          </Routes>
        </main>

        <Footer />

        <ToastContainer position="top-right" autoClose={3000} />

      </div>
    </Router>
  );
}

export default App;
