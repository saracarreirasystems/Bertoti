-- Script com dados de exemplo para testar o assistente
-- Execute este script após criar o banco de dados

USE SAMBOLDAPI;

-- Inserir usuários de exemplo
INSERT INTO usuario (email, nome, curso, senha, perfil, _status) VALUES
('joao.silva@fatec.sp.gov.br', 'João Silva', 'Análise e Desenvolvimento de Sistemas', 'senha123', 0, 1),
('maria.santos@fatec.sp.gov.br', 'Maria Santos', 'Análise e Desenvolvimento de Sistemas', 'senha123', 0, 1),
('pedro.oliveira@fatec.sp.gov.br', 'Pedro Oliveira', 'Desenvolvimento de Software Multiplataforma', 'senha123', 0, 1),
('ana.costa@fatec.sp.gov.br', 'Ana Costa', 'Análise e Desenvolvimento de Sistemas', 'senha123', 0, 1),
('carlos.souza@fatec.sp.gov.br', 'Carlos Souza', 'Banco de Dados', 'senha123', 0, 1),
('prof.roberto@fatec.sp.gov.br', 'Prof. Roberto Lima', 'Professor de Engenharia de Software', 'senha123', 1, 1),
('prof.fernanda@fatec.sp.gov.br', 'Prof. Fernanda Alves', 'Professora de Banco de Dados', 'senha123', 1, 1),
('prof.marcelo@fatec.sp.gov.br', 'Prof. Marcelo Dias', 'Professor de Desenvolvimento Web', 'senha123', 1, 1);

-- Inserir solicitações
INSERT INTO solicitacao (email_aluno, email_prof, _status, data) VALUES
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 1, '2024-03-15 10:30:00'),
('maria.santos@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 1, '2024-03-15 11:00:00'),
('pedro.oliveira@fatec.sp.gov.br', 'prof.fernanda@fatec.sp.gov.br', 1, '2024-03-16 09:00:00'),
('ana.costa@fatec.sp.gov.br', 'prof.marcelo@fatec.sp.gov.br', 0, '2024-03-16 14:30:00');

-- Inserir mensagens de chat
INSERT INTO chat_mensagem (email_aluno, email_prof, sender, texto, created_at) VALUES
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'aluno', 'Bom dia, professor! Gostaria de tirar uma dúvida sobre o TG.', '2024-03-20 09:00:00'),
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'prof', 'Bom dia, João! Claro, pode perguntar.', '2024-03-20 09:05:00'),
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'aluno', 'Qual o prazo para entrega da primeira seção?', '2024-03-20 09:06:00'),
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'prof', 'O prazo é dia 30 de março.', '2024-03-20 09:10:00'),
('maria.santos@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'aluno', 'Professor, enviei o documento da seção 1.', '2024-03-21 10:00:00'),
('maria.santos@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'prof', 'Recebi, Maria! Vou avaliar e dar feedback em breve.', '2024-03-21 10:15:00');

-- Inserir documentos
INSERT INTO documento (email_aluno, email_prof, nome_arquivo, caminho_arquivo, tamanho_bytes, tipo_arquivo, data_upload) VALUES
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'secao1_joao.pdf', '/uploads/joao/secao1.pdf', 245000, 'application/pdf', '2024-03-18 14:30:00'),
('maria.santos@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 'proposta_tg_maria.docx', '/uploads/maria/proposta.docx', 128000, 'application/docx', '2024-03-19 16:00:00'),
('pedro.oliveira@fatec.sp.gov.br', 'prof.fernanda@fatec.sp.gov.br', 'secao2_pedro.pdf', '/uploads/pedro/secao2.pdf', 310000, 'application/pdf', '2024-03-20 11:00:00');

-- Inserir dados de seção 0 (perfil do aluno)
INSERT INTO secao_0 (email_aluno, nome_completo, idade, curso, email_contato, linkedin, github, motivacao_fatec, historico_academico, updated_at) VALUES
('joao.silva@fatec.sp.gov.br', 'João Silva', 22, 'Análise e Desenvolvimento de Sistemas', 'joao.silva@email.com', 'linkedin.com/in/joaosilva', 'github.com/joaosilva', 'Sempre tive interesse em tecnologia e programação', 'Formado em técnico de informática', '2024-03-15 10:00:00'),
('maria.santos@fatec.sp.gov.br', 'Maria Santos', 21, 'Análise e Desenvolvimento de Sistemas', 'maria.santos@email.com', 'linkedin.com/in/mariasantos', 'github.com/mariasantos', 'Quero trabalhar com desenvolvimento de sistemas', 'Cursando 4º semestre', '2024-03-15 11:00:00');

-- Inserir seções de API
INSERT INTO secao_api (email_aluno, numero_secao, empresa_parceira, problema, solucao, repositorio_github, tecnologias_utilizadas, contribuicoes_pessoais, hard_skills, soft_skills, updated_at) VALUES
('joao.silva@fatec.sp.gov.br', 1, 'TechCorp', 'Sistema de gestão desatualizado', 'Desenvolvimento de novo sistema web', 'github.com/joaosilva/projeto1', 'Java, Spring Boot, MySQL', 'Desenvolvimento do backend completo', 'Java, Spring, MySQL', 'Trabalho em equipe, Comunicação', '2024-03-18 14:00:00'),
('maria.santos@fatec.sp.gov.br', 1, 'DataSoft', 'Falta de integração entre sistemas', 'API REST para integração', 'github.com/mariasantos/projeto1', 'Node.js, Express, MongoDB', 'Criação da arquitetura da API', 'Node.js, REST API, MongoDB', 'Proatividade, Organização', '2024-03-19 15:00:00');

-- Inserir feedbacks
INSERT INTO feedback (email_aluno, email_prof, numero_secao, comentario, status_aprovacao, created_at, updated_at) VALUES
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 0, 'Ótimo perfil! Muito completo e bem estruturado.', 1, '2024-03-19 10:00:00', '2024-03-19 10:00:00'),
('joao.silva@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 1, 'Bom trabalho na seção 1. Sugiro adicionar mais detalhes técnicos.', 1, '2024-03-20 11:00:00', '2024-03-20 11:00:00'),
('maria.santos@fatec.sp.gov.br', 'prof.roberto@fatec.sp.gov.br', 0, 'Perfil aprovado. Continue assim!', 1, '2024-03-19 16:00:00', '2024-03-19 16:00:00');

-- Verificar dados inseridos
SELECT 'Usuários cadastrados:' as Info, COUNT(*) as Total FROM usuario
UNION ALL
SELECT 'Solicitações:', COUNT(*) FROM solicitacao
UNION ALL
SELECT 'Mensagens de chat:', COUNT(*) FROM chat_mensagem
UNION ALL
SELECT 'Documentos:', COUNT(*) FROM documento
UNION ALL
SELECT 'Seções 0:', COUNT(*) FROM secao_0
UNION ALL
SELECT 'Seções API:', COUNT(*) FROM secao_api
UNION ALL
SELECT 'Feedbacks:', COUNT(*) FROM feedback;
