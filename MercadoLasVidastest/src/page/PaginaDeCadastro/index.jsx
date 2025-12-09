import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "react-toastify";
import api from "../../service/api";
import "./style.css";


// 🔥 ESQUEMA CORRIGIDO — FUNCIONA DE VERDADE
const esquemaDeCadastro = yup.object({
  nome: yup
    .string()
    .required("O nome é obrigatório.")
    .min(3, "O nome deve ter pelo menos 3 caracteres."),

  email: yup
    .string()
    .required("O e-mail é obrigatório.")
    .email("Formato de e-mail inválido."),

  telefone: yup
    .string()
    .required("O telefone é obrigatório.")
    .matches(/^\d{10,12}$/, "Telefone inválido. Use apenas números. Ex.: 71988998899"),

  senha: yup
    .string()
    .required("A senha é obrigatória.")
    .min(6, "A senha deve ter pelo menos 6 caracteres."),

  confirmarSenha: yup
    .string()
    .required("Confirme a senha.")
    .oneOf([yup.ref("senha"), null], "As senhas não conferem."),
});

function PaginaDeCadastro() {
  const {
    register: registrarCampo,
    handleSubmit: lidarComEnvioDoFormulario,
    formState: { errors: errosDoFormulario, isSubmitting: estaEnviando },
    setError: definirErroNoCampo,
    reset: limparCamposDoFormulario,
  } = useForm({
    resolver: yupResolver(esquemaDeCadastro),
    defaultValues: { nome: "", email: "", telefone: "", senha: "", confirmarSenha: "" },
  });

  async function enviarDados(dadosDoFormulario) {
    const dadosParaApi = {
      nome: dadosDoFormulario.nome,
      email: dadosDoFormulario.email,
      telefone: dadosDoFormulario.telefone,
      senha: dadosDoFormulario.senha,
    };

    try {
      const resposta = await api.post("/usuarios", dadosParaApi);

      toast.success(resposta.data.mensagem || "Usuário cadastrado com sucesso!");
      limparCamposDoFormulario();

    } catch (erro) {
      const codigoDeStatus = erro?.response?.status;
      const mensagemDoServidor =
        erro?.response?.data?.mensagem || "Erro ao cadastrar usuário.";

      if (codigoDeStatus === 409) {
        definirErroNoCampo("email", {
          type: "server",
          message: mensagemDoServidor,
        });
      }

      toast.error(mensagemDoServidor);
      console.error("Erro no cadastro:", erro);
    }
  }

  return (
    <div className="cadastro-container">
      <h1>Cadastro de Usuário</h1>

      <form noValidate onSubmit={lidarComEnvioDoFormulario(enviarDados)}>
        
        {/* Nome */}
        <div className="form-group">
          <label htmlFor="campo-nome">Nome</label>
          <input
            id="campo-nome"
            type="text"
            placeholder="Ex.: Maria Silva"
            {...registrarCampo("nome")}
          />
        </div>
        {errosDoFormulario.nome && (
          <p className="error-message">{errosDoFormulario.nome.message}</p>
        )}

        {/* Telefone */}
        <div className="form-group">
          <label htmlFor="campo-telefone">Telefone</label>
          <input
            id="campo-telefone"
            type="text"
            placeholder="Ex.: 71988998899"
            {...registrarCampo("telefone")}
          />
        </div>

        {errosDoFormulario.telefone && (
          <p className="error-message">{errosDoFormulario.telefone.message}</p>
        )}

        {/* E-mail */}
        <div className="form-group">
          <label htmlFor="campo-email">E-mail</label>
          <input
            id="campo-email"
            type="email"
            placeholder="exemplo@dominio.com"
            {...registrarCampo("email")}
          />
        </div>
        {errosDoFormulario.email && (
          <p className="error-message">{errosDoFormulario.email.message}</p>
        )}

        {/* Senha */}
        <div className="form-group">
          <label htmlFor="campo-senha">Senha</label>
          <input
            id="campo-senha"
            type="password"
            placeholder="Mínimo 6 caracteres"
            {...registrarCampo("senha")}
          />
        </div>
        {errosDoFormulario.senha && (
          <p className="error-message">{errosDoFormulario.senha.message}</p>
        )}

        {/* Confirmar Senha */}
        <div className="form-group">
          <label htmlFor="campo-confirmar-senha">Confirmar Senha</label>
          <input
            id="campo-confirmar-senha"
            type="password"
            placeholder="Repita a senha"
            {...registrarCampo("confirmarSenha")}
          />
        </div>
        {errosDoFormulario.confirmarSenha && (
          <p className="error-message">
            {errosDoFormulario.confirmarSenha.message}
          </p>
        )}

        <button type="submit" disabled={estaEnviando}>
          {estaEnviando ? "Cadastrando..." : "Cadastrar"}
        </button>
      </form>
    </div>
  );
}

export default PaginaDeCadastro;
