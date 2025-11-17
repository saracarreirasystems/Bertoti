# 🤖 Assistente de Banco de Dados com IA (Ollama + Java + MySQL)

Sistema completo que permite fazer perguntas em **linguagem natural** sobre um banco de dados MySQL, usando Inteligência Artificial local (Ollama) para interpretar e responder.

## 🎯 O que este projeto faz?

```
Você pergunta: "Quantos alunos estão cadastrados?"
       ↓
IA entende e gera: SELECT COUNT(*) FROM usuario WHERE perfil=0
       ↓
Executa no MySQL
       ↓
Responde: "Há 5 alunos cadastrados no sistema! 👨‍🎓"
```

---

## ⚡ INÍCIO RÁPIDO (3 minutos)

### 1️⃣ Instalar Ollama

**Windows:**
```bash
# Baixe e instale: https://ollama.com/download
# Depois no terminal:
ollama pull gemma3:12b
ollama serve
```

**Linux:**
```bash
curl -fsSL https://ollama.com/install.sh | sh
ollama pull gemma3:12b
ollama serve
```

⚠️ **IMPORTANTE:** Deixe `ollama serve` rodando em um terminal separado!

### 2️⃣ Configurar MySQL

Edite o arquivo: `src/main/java/com/sambold/config/DatabaseConfig.java`

```java
private static final String USER = "root";        // ← SEU USUÁRIO
private static final String PASSWORD = "sua_senha"; // ← SUA SENHA
```

Execute o script SQL do seu banco de dados, depois (opcional):
```bash
mysql -u root -p < dados_exemplo.sql
```

### 3️⃣ Executar

**No VS Code:**
- Pressione `F5` ou clique em "Run" no arquivo `Main.java`

**Ou no terminal:**
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.sambold.Main"
```

---

## 💬 Exemplos de Uso

```
💬 Você: Quantos usuários existem?
🤖 Assistente: Existem 8 usuários cadastrados! 👥

💬 Você: Liste todos os alunos de ADS
🤖 Assistente: Encontrei 3 alunos:
   • João Silva
   • Maria Santos
   • Ana Costa

💬 Você: Mostre as últimas 5 mensagens do chat
🤖 Assistente: [Mostra as mensagens formatadas]

💬 Você: info
📊 [Mostra estatísticas de todas as tabelas]

💬 Você: sair
👋 Até logo!
```

---

## 📁 Estrutura do Projeto

```
ollama-database-assistant/
├── .vscode/                      # Configurações VS Code
│   ├── launch.json              # ← Run com F5
│   ├── settings.json
│   └── extensions.json
├── src/main/java/com/sambold/
│   ├── Main.java                # 🚀 EXECUTE ESTE
│   ├── TestAssistant.java       # 🧪 Testes
│   ├── config/
│   │   └── DatabaseConfig.java  # ⚙️ CONFIGURE AQUI
│   ├── ollama/
│   │   └── OllamaClient.java    # Cliente Ollama API
│   ├── database/
│   │   ├── DatabaseSchemaProvider.java
│   │   └── DatabaseQueryExecutor.java
│   └── assistant/
│       └── DatabaseAssistant.java  # Lógica principal
├── pom.xml                      # Maven dependencies
├── dados_exemplo.sql            # Dados de teste
└── README.md                    # Este arquivo
```

---

## 🔧 Configurações Importantes

### DatabaseConfig.java - EDITE ESTE ARQUIVO!
```java
// Localização: src/main/java/com/sambold/config/DatabaseConfig.java

private static final String URL = "jdbc:mysql://localhost:3306/SAMBOLDAPI";
private static final String USER = "root";        // ← ALTERE
private static final String PASSWORD = "";        // ← ALTERE
```

### OllamaClient.java - Modelo de IA
```java
// Se quiser usar um modelo diferente:
private static final String MODEL = "gemma3:12b";  // ou "llama3.2", "gemma2:9b"
```

---

## 🎮 Como Usar no VS Code

### Método 1: Pressione F5
- Abre o arquivo `src/main/java/com/sambold/Main.java`
- Pressiona `F5`
- Pronto! 🚀

### Método 2: Run and Debug
1. Clique no ícone "Run and Debug" (Ctrl+Shift+D)
2. Selecione "Run Main"
3. Clique no botão verde ▶️

### Método 3: Terminal Integrado
```bash
# Abra o terminal (Ctrl+`)
mvn clean install
mvn exec:java -Dexec.mainClass="com.sambold.Main"
```

---

## 🧪 Executar Testes

```bash
# Via terminal
mvn exec:java -Dexec.mainClass="com.sambold.TestAssistant"

# Ou no VS Code
# Abra TestAssistant.java e pressione F5
```

---

## ⚠️ Requisitos

- ✅ Java 17 ou superior
- ✅ Maven 3.6+
- ✅ MySQL 8.0+
- ✅ Ollama instalado
- ✅ 8GB RAM (recomendado: 16GB)
- ✅ ~10GB espaço em disco (para o modelo)

---

## 🔒 Segurança

- ✅ Apenas queries **SELECT** permitidas
- ✅ Validação anti-SQL injection básica
- ✅ Limite de 50 linhas por consulta
- ✅ BLOBs não são expostos diretamente

---

## 🐛 Problemas Comuns

### ❌ "Ollama não está rodando"
```bash
# Solução: Abra outro terminal e execute
ollama serve
```

### ❌ "Cannot connect to database"
```
1. Verifique se MySQL está rodando
2. Confira DatabaseConfig.java
3. Teste com MySQL Workbench
```

### ❌ "Model not found"
```bash
ollama pull gemma3:12b
```

### ⏱️ Primeira pergunta muito lenta
```
É NORMAL! O modelo está carregando (5-15 segundos)
Próximas perguntas serão rápidas (2-3 segundos)
```

---

## 📊 Banco de Dados (SAMBOLDAPI)

### Tabelas Principais:
- `usuario` - Alunos e professores
- `solicitacao` - Pedidos de orientação
- `chat_mensagem` - Mensagens
- `documento` - Arquivos
- `secao_0` - Perfil do aluno
- `secao_api` - Seções de projetos (1-6)
- `feedback` - Avaliações

### Dados de Exemplo:
Execute `dados_exemplo.sql` para popular o banco com:
- 8 usuários (5 alunos + 3 professores)
- 6 mensagens de chat
- 3 documentos
- Feedbacks e seções

---

## 🚀 Próximos Passos

1. ✅ Configure `DatabaseConfig.java`
2. ✅ Inicie Ollama (`ollama serve`)
3. ✅ Execute o projeto (F5)
4. ✅ Faça perguntas!

---

## 📚 Comandos Úteis

### Durante a execução:
- `info` - Estatísticas do banco
- `ajuda` - Menu de comandos
- `limpar` - Limpa a tela
- `sair` - Encerra

### Perguntas de exemplo:
- "Quantos usuários existem?"
- "Liste todos os professores"
- "Mostre documentos enviados hoje"
- "Quais alunos têm GitHub cadastrado?"
- "Últimas 5 mensagens do chat"

---

## 🎓 Projeto Acadêmico

Desenvolvido para: **Engenharia de Software**  
Objetivo: Demonstrar integração de IA com Banco de Dados

### Tecnologias:
- Ollama (Gemma 3)
- Java 17
- MySQL
- Maven
- OkHttp
- Gson

---

## 📄 Licença

Projeto acadêmico - Livre para uso educacional

---

## 💡 Dicas

- Use perguntas claras e específicas
- A primeira execução demora mais (carregamento do modelo)
- Consulte o terminal para ver as queries SQL geradas
- Use 'info' para ver estatísticas rápidas

---

**Pronto para começar? Configure `DatabaseConfig.java` e pressione F5! 🚀**
