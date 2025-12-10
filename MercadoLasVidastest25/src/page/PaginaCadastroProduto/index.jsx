import './style.css';

import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import api from "../../service/api";

// ESQUEMA DE VALIDAÇÃO PARA PRODUTO
const esquemaDeCadastroProduto = yup.object({
  nome: yup
    .string()
    .required("O nome do produto é obrigatório.")
    .min(3, "O nome deve ter pelo menos 3 caracteres."),
    
  preco: yup
    .number()
    .typeError("Informe um valor válido.")
    .positive("O preço deve ser maior que zero.")
    .required("O preço é obrigatório."),

  quantidade: yup
    .number()
    .typeError("Informe uma quantidade válida.")
    .integer("A quantidade deve ser um número inteiro.")
    .min(1, "A quantidade mínima é 1.")
    .required("A quantidade é obrigatória."),

  descricao: yup
    .string()
    .min(5, "A descrição deve ter pelo menos 5 caracteres.")
    .required("A descrição é obrigatória."),

  carregarfoto: yup
    .mixed()
    .typeError("Adicione uma imagem ao produto.")
    .notRequired(),
});

function PaginaDeCadastroProduto() {
  const {
    register: registrarCampo,
    handleSubmit: lidarComEnvioDoFormulario,
    formState: { errors: errosDoFormulario, isSubmitting: estaEnviando },
    reset: limparCamposDoFormulario,
  } = useForm({
    resolver: yupResolver(esquemaDeCadastroProduto),
    defaultValues: {
      nome: "",
      preco: "",
      quantidade: "",
      descricao: "",
      carregarfoto: ""
    },
  });

  async function enviarDados(dadosDoFormulario) {
  const formData = new FormData();
  formData.append("nome", dadosDoFormulario.nome);
  formData.append("preco", dadosDoFormulario.preco);
  formData.append("quantidade", dadosDoFormulario.quantidade);
  formData.append("descricao", dadosDoFormulario.descricao);
  formData.append("foto", dadosDoFormulario.carregarfoto[0]); // <-- FOTO

  try {
    const resposta = await api.post("/produtos", formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });

    toast.success("Produto cadastrado com sucesso!");
    limparCamposDoFormulario();

  } catch (erro) {
    toast.error("Erro ao cadastrar produto.");
  }
}



  return (
    <div className="cadastro-container">
      <h1>Cadastro de Produto</h1>

      <form noValidate onSubmit={lidarComEnvioDoFormulario(enviarDados)}>

        {/* Nome */}
        <div className="form-group">
          <label htmlFor="campo-nome">Nome do Produto</label>
          <input
            id="campo-nome"
            type="text"
            placeholder="Ex.: Arroz Branco 5kg"
            {...registrarCampo("nome")}
          />
        </div>
        {errosDoFormulario.nome && (
          <p className="error-message">{errosDoFormulario.nome.message}</p>
        )}

        {/* Preço */}
        <div className="form-group">
          <label htmlFor="campo-preco">Preço (R$)</label>
          <input
            id="campo-preco"
            type="number"
            step="0.01"
            placeholder="Ex.: 19.90"
            {...registrarCampo("preco")}
          />
        </div>
        {errosDoFormulario.preco && (
          <p className="error-message">{errosDoFormulario.preco.message}</p>
        )}

        {/* Quantidade */}
        <div className="form-group">
          <label htmlFor="campo-quantidade">Quantidade</label>
          <input
            id="campo-quantidade"
            type="number"
            placeholder="Ex.: 50"
            {...registrarCampo("quantidade")}
          />
        </div>
        {errosDoFormulario.quantidade && (
          <p className="error-message">{errosDoFormulario.quantidade.message}</p>
        )}

        {/* Descrição */}
        <div className="form-group">
          <label htmlFor="campo-descricao">Descrição</label>
          <textarea
            id="campo-descricao"
            placeholder="Ex.: Produto de alta qualidade..."
            {...registrarCampo("descricao")}
          />
        </div>
        {errosDoFormulario.descricao && (
          <p className="error-message">{errosDoFormulario.descricao.message}</p>
        )}
        {/* Carregar Foto */}
        <div className="form-group">
          <label htmlFor="campo-carregar-foto">Carregar Foto</label>
          <input
            id="campo-carregar-foto"
            type="file"
            accept="image/*"
            {...registrarCampo("carregarfoto")}
          />
        </div>
        {errosDoFormulario.carregarfoto && (
          <p className="error-message">{errosDoFormulario.carregarfoto.message}</p>
        )}

        <button type="submit" disabled={estaEnviando}>
          {estaEnviando ? "Cadastrando..." : "Cadastrar Produto"}
        </button>

      </form>
    </div>
  );
}

export default PaginaDeCadastroProduto;
