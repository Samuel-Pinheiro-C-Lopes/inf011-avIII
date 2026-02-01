# Framework de Gestão de Documentos - INF011

## Questão I

### Padrão: Strategy

**Justificativa**: A classe Autenticador original tinha uma sequência de if-elses para determinar a regra de geração de número de protocolo, violando o Open/Closed e dificultando a manutenção. O padrão Strategy foi escolhido para encapsular diferentes algoritmos em classes separadas, permitindo adicionar novas estratégias sem modificar código existente.

**Participantes**:
- **Strategy**: AutenticadorStrategy (interface)
- **ConcreteStrategy**: DiaProprietarioHashAutenticadorStrategy, HashYearAutenticadorStrategy, PadraoAutenticadorStrategy, PrivacidadeHashAutenticadorStrategy
- **Context**: Autenticador
- **Client**: MyGerenciadorDocumentoUI, AbstractGerenciadorDocumentosUI

## Questão II

### Padrões: Command, Composite e Template Method

**Justificativa**: 
- **Command**: Encapsula operações como objetos, permitindo log, undo/redo e desacoplamento
- **Composite**: Permite tratar comandos individuais e composições uniformemente, habilitando operações compostas (macros)
- **Template Method**: Define o esqueleto do algoritmo garantindo log automático e ordem consistente de execução

### Padrão Command

**Participantes**:
- **Command**: DocumentoCommand (interface)
- **ConcreteCommand**: CriarDocumentoCommand, SalvarDocumentoCommand, AssinarDocumentoCommand, ProtegerDocumentoCommand, TornarUrgenteDocumentoCommand
- **Receiver**: GerenciadorDocumentoModel
- **Invoker**: DocumentoCommandManager
- **Client**: MyGerenciadorDocumentoUI

### Padrão Composite

**Participantes**:
- **Component**: DocumentoCommand (interface)
- **Composite**: BaseDocumentoCommand (classe abstrata), CriarDocumentoCommand, SalvarDocumentoCommand, AssinarDocumentoCommand, ProtegerDocumentoCommand e TornarUrgenteDocumentoCommand (Implementações)
- **Leaf**: Não há por hora, porém o padrão permite a sua inserção sem acarretar mudanças no lado do cliente

### Padrão Template Method

**Participantes**:
- **AbstractClass**: BaseDocumentoCommand
- **ConcreteClass**: CriarDocumentoCommand, SalvarDocumentoCommand, AssinarDocumentoCommand, ProtegerDocumentoCommand, TornarUrgenteDocumentoCommand

O Template Method define o algoritmo em execute() e undo() (métodos finais), enquanto as subclasses implementam os hooks executeHook(), revertHook() e getLogHook().

## Funcionalidades

- **Log**: Todas as operações são registradas em logs.txt automaticamente
- **Undo/Redo**: Sistema completo de desfazer e refazer operações
- **Macros**: Operações compostas como "Alterar e Assinar" e "Priorizar"
- **Consolidar**: Limpa o histórico de undo/redo
