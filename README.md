# ModuloTI
Projeto módulo de TI para sistema ERP

Tela de Login
Classes relacionadas:
•	Entity:
o	Login
•	Controller:
o	LoginController
•	Repository:
o	LoginRepository
 

Tela p/ editar Login
Editar o cadastro do usuário que efetuou o login.
 

Tela de Consulta de chamados
Classes relacionadas:
•	Entity:
o	Chamado
•	Controller:
o	ChamadoController
•	Repository:
o	ChamadoRepository
Nesta tela é possível adicionar novos chamado, edita-los, apaga-los, filtra-los e enviar e-mails avulsos.
Ao incluir um novo chamado automaticamente o é enviado um e-mail para o solicitante e uma O.S (Ordem de serviço) é gerada.
 

Tela de inclusão/ Edição de chamado
O incluir ou editar um chamado um e-mail automático será disparado, nela é possível selecionar o técnico responsável e o status do chamado.
 

Tela de Consulta de O.S (Ordem de Serviço)
Classes relacionadas:
•	Entity:
o	OrdemServico;
•	Controller:
o	OrdemServicoController;
•	Repository:
o	OrdemServicoRepository
A partir de cada O.S aberta é possível o técnico está incluindo um apontamento.
 

Tela de Relatório de O.S
Todos os Chamados e suas respectivas O.S são listadas aqui, sendo possível filtrar por data.
 
Tela de Consulta de Apontamentos 
Classes relacionadas:
•	Entity:
o	Apontamento;
•	Repository:
o	ApontamentoRepository
Nela é possível editar, enviar e-mail, filtrar, e excluir um apontamento.
 

Tela de Edição e inclusão de apontamentos
 

Tela de consulta de técnico
Classes relacionadas:
•	Entity:
o	Tecnico
•	Controller:
o	TecnicoController;
•	Repository:
o	TecnicoRepository

Nela é possível filtrar o técnico por nome, incluir e editar um técnico e deleta-lo.
 

Tela de inclusão de edição de técnico
 

Tela de Envio de E-mail
Classes relacionadas:
•	Entity:
o	SenderMail
•	Configuration:
o	AppWebConfiguration;
•	Controller
o	ChamadoController
Nela é possível enviar e-mail avulsos.
