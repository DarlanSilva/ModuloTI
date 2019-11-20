# Módulo de TI 

### Projeto módulo de TI para sistema ERP 

## Tela de Login 
### Classes relacionadas:
- **Entity:** 
    - [Login;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Entity/Login.java "Login") 
- **Controller:** 
    - [LoginController;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Controller/LoginController.java "LoginController")  
-  **Repository:** 
    -   [LoginRepository;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Repository/LoginRepository.java "LoginRepository")
- **Configuration**
  -   [SecurityConfig.](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Configuration/SecurityConfig.java "SecurityConfig")     

![Página de Login TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/login-page.jpg)

## Tela p/ editar Login 

Tela de edição do cadastro do usuário que efetuou o login.

![Página de edição Login TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/Login-Editar.jpg)

## Tela de Consulta de chamados 

### Classes relacionadas: 
- **Entity:** 
  - [Chamado;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Entity/Chamado.java "Chamado")  
- **Controller:** 
  - [ChamadoController;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Controller/ChamadoController.java "ChamadoController")  
- **Repository:** 
  - [ChamadoRepository.](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Repository/ChamadoRepository.java "ChamadoRepository")  

Nesta tela é possível adicionar novos chamado, edita-los, apaga-los, filtra-los e enviar e-mails avulsos. 
Ao incluir um novo chamado automaticamente o é enviado um e-mail para o solicitante e uma O.S (Ordem de serviço) é gerada. 

![Página consulta de chamados TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/chamado-page.jpg)

## Tela de inclusão/ Edição de chamado 
O incluir ou editar um chamado um e-mail automático será disparado, nela é possível selecionar o técnico responsável e o status do chamado. 

![Página de edição/ adição do chamado TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/chamado-editar.jpg)

## Tela de Consulta de O.S (Ordem de Serviço) 

### Classes relacionadas: 
- **Entity:**
  - [OrdemServico](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Entity/OrdemServico.java"OrdemServico")
- **Controller:**
  - [OrdemServicoController;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Controller/OrdemServicoController.java "OrdemServicoController")
 - **Repository:**
    - [OrdemServicoRepository.](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Repository/OrdemServicoRepository.java "OrdemServicoRepository")

A partir de cada O.S aberta é possível o técnico está incluindo um apontamento. 

![Página Ordem de serviço TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/os-page.jpg)

## Tela de Relatório de O.S 
Todos os Chamados e suas respectivas O.S são listadas aqui, sendo possível filtrar por data. 

![Página de relatório de Ordem de serviço TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/relatorio-os.jpg)

## Tela de Consulta de Apontamentos  

### Classes relacionadas: 
- **Entity:**
  - [Apontamento;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Entity/Apontamento.java "Apontamento") 
- **Repository:** 
  - [ApontamentoRepository](https://github.com/DarlanSilva/ModuloTI/tree/master/src/main/java/br/com/senac/moduloTI/Repository "ApontamentoRepository")  

Nela é possível editar, enviar e-mail, filtrar, e excluir um apontamento. 

![Página de apontamentos TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/apontamento-consultar.jpg)

## Tela de Edição/ Inclusão de apontamentos 

![Página de edição/adiçao de apontamentos TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/apontamento-editar.jpg) 

## Tela de consulta de técnico 

### Classes relacionadas: 
- **Entity:** 
  - [Tecnico;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Entity/Tecnico.java "Tecnico") 
- **Controller:**
  - [TecnicoController;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Controller/TecnicoController.java"TecnicoController") 
- **Repository:** 
  - [TecnicoRepository.](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Repository/TecnicoRepository.java"TecnicoRepository")  
  
Nela é possível filtrar o técnico por nome, incluir e editar um técnico e deleta-lo. 

![Página de consulta de técnicos TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/tecnico-consultar.jpg) 

## Tela de inclusão de edição de técnico 

![Página de adição/edição de técnicos TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/tecnico-editar.jpg) 

## Tela de Envio de E-mail 

### Classes relacionadas: 
- **Entity:** 
  - [SenderMail;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Entity/SenderMail.java "Tecnico") 
- **Configuration:**
  - [AppWebConfiguration;](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Configuration/AppWebConfiguration.java "AppWebConfiguration") 
- **Controller:** 
  - [ChamadoController.](https://github.com/DarlanSilva/ModuloTI/blob/master/src/main/java/br/com/senac/moduloTI/Controller/ChamadoController.java"ChamadoController") 

Nela é possível enviar e-mail avulsos. 

![Página de envio de e-mail TechMode](https://github.com/DarlanSilva/ModuloTI/blob/master/templates-prints/mail.jpg) 


 
