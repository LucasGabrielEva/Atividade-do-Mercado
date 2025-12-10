import React, { useState, useEffect } from 'react';
import api from '../../service/api';
import './style.css'; 
import { toast } from 'react-toastify'; 

function ListaUsuariosPage() {
  const [usuarios, setUsuarios] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchUsuarios() {
      try {
        const response = await api.get('/usuarios');
        setUsuarios(response.data);
        setLoading(false);
      } catch (error) {
        console.error("Erro ao buscar usuarios:", error);
        const msgErro = error?.response?.data?.mensagem || "Erro ao buscar usuários.";
        toast.error(msgErro); 
        setLoading(false);
      }
    }
    fetchUsuarios();
  }, []);

  if (loading) {
    return <div className="listaContainer"><h2>Carregando...</h2></div>;
  }

  return (
    <div className="listaContainer"> 
      <h1>Lista de Usuários</h1>
      {usuarios.length === 0 ? (
        <p>Nenhum usuário cadastrado.</p>
      ) : (
        <table className="tabelaUsuarios">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Email</th>
              <th>Telefone</th>
            </tr>
          </thead>
          <tbody>
            {usuarios.map(usuario => (
              <tr key={usuario.email}> 
                <td>{usuario.nome}</td>
                <td>{usuario.email}</td>
                <td>{usuario.telefone}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default ListaUsuariosPage;