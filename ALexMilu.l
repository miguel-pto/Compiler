package alex;

import asint.ClaseLexica;
import errors.GestionErroresMilu;

%%
%cup
%line
%column
%class AnalizadorLexicoMilu
%unicode
%public

%{
  private GestionErroresMilu errores;
  private ALexOperations ops;

  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  
  public void fijaGestionErrores(GestionErroresMilu errores) {
    this.errores = errores;
  }
%}

%init{
  ops = new ALexOperations(this);
%init}

%eofval{
  return ops.unidadEof();
%eofval}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = 0 | {digitoPositivo}{digito}*
parteDecimal = {digito}*{digitoPositivo}
blank = [ \t\r\b\n]
comentario = "//"[^\n]*
intnum = {parteEntera}
floatnum = {parteEntera}\.{parteDecimal}
comma  = \,
colon = \:
scolon = \;
punto = \.
suma = \+
resta = \-
mult = \*
div = \/
mod = \%
neg = \!
ref = \&
igual = \=\=
distinto = \!\=
menor = \<
mayor = \>
menorIgual = \<\=
mayorIgual = \>\=
and = \&\&
or = \|\|
pap = \(
pcierre = \)
cap = \[
ccierre = \]
bap = \{
bcierre = \}
asig = \=
int = int
bool = bool
float = float
array = array
var = var
def = def
if = if
else = else
while = while
for = for
return = return
as = as
struct = struct
de = de
void = void
new = new
true = true
false = false
free = free
print = print
read = read
identificador = ({letra} | \_)({letra} | {digito} | \_)*

%%
{blank}         {}
{comentario}    {}
{var}           {return ops.unidadVar();}
{def}           {return ops.unidadDef();}
{if}            {return ops.unidadIf();}
{else}          {return ops.unidadElse();}
{while}         {return ops.unidadWhile();}
{for}           {return ops.unidadFor();}
{return}        {return ops.unidadReturn();}
{as}            {return ops.unidadAs();}
{struct}        {return ops.unidadStruct();}
{new}           {return ops.unidadNew();}
{de}            {return ops.unidadDe();}
{int}           {return ops.unidadInt();}
{bool}          {return ops.unidadBool();}
{float}         {return ops.unidadFloat();}
{void}          {return ops.unidadVoid();}
{array}         {return ops.unidadArray();}
{true}          {return ops.unidadTrue();}
{false}         {return ops.unidadFalse();}
{free}          {return ops.unidadFree();}
{print}         {return ops.unidadPrint();}
{read}          {return ops.unidadRead();}
{suma}          {return ops.unidadSuma();}
{resta}         {return ops.unidadResta();}
{mult}          {return ops.unidadMult();}
{div}           {return ops.unidadDiv();}
{mod}           {return ops.unidadMod();}
{neg}           {return ops.unidadNeg();}
{and}           {return ops.unidadAnd();}
{or}            {return ops.unidadOr();}
{ref}           {return ops.unidadRef();}
{igual}         {return ops.unidadIgual();}
{distinto}      {return ops.unidadDistinto();}
{menorIgual}    {return ops.unidadMenorIgual();}
{mayorIgual}    {return ops.unidadMayorIgual();}
{menor}         {return ops.unidadMenor();}
{mayor}         {return ops.unidadMayor();}
{asig}          {return ops.unidadAsig();}
{pap}           {return ops.unidadPAp();}
{pcierre}       {return ops.unidadPCierre();}
{cap}           {return ops.unidadCAp();}
{ccierre}       {return ops.unidadCCierre();}
{bap}           {return ops.unidadBAp();}
{bcierre}       {return ops.unidadBCierre();}
{comma}         {return ops.unidadComa();}
{colon}         {return ops.unidadColon();}
{scolon}        {return ops.unidadSColon();}
{punto}         {return ops.unidadPunto();}
{intnum}        {return ops.unidadIntNum();}
{floatnum}      {return ops.unidadFloatNum();}
{identificador} {return ops.unidadId();}

[^]             {errores.errorLexico(fila(), columna(), lexema());}
