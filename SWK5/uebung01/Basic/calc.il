
//  Microsoft (R) .NET Framework IL Disassembler.  Version 4.6.81.0
//  Copyright (c) Microsoft Corporation. Alle Rechte vorbehalten.



// Metadata version: v4.0.30319
.assembly extern mscorlib
{
  .publickeytoken = (B7 7A 5C 56 19 34 E0 89 )                         // .z\V.4..
  .ver 4:0:0:0
}
.assembly Calc
{
  .custom instance void [mscorlib]System.Runtime.CompilerServices.CompilationRelaxationsAttribute::.ctor(int32) = ( 01 00 08 00 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Runtime.CompilerServices.RuntimeCompatibilityAttribute::.ctor() = ( 01 00 01 00 54 02 16 57 72 61 70 4E 6F 6E 45 78   // ....T..WrapNonEx
                                                                                                             63 65 70 74 69 6F 6E 54 68 72 6F 77 73 01 )       // ceptionThrows.

  // --- The following custom attribute is added automatically, do not uncomment -------
  //  .custom instance void [mscorlib]System.Diagnostics.DebuggableAttribute::.ctor(valuetype [mscorlib]System.Diagnostics.DebuggableAttribute/DebuggingModes) = ( 01 00 07 01 00 00 00 00 ) 

  .hash algorithm 0x00008004
  .ver 0:0:0:0
}
.module Calc.dll
// MVID: {22FB5C2A-4955-455C-A4C7-661F7DFE95A4}
.imagebase 0x10000000
.file alignment 0x00000200
.stackreserve 0x00100000
.subsystem 0x0003       // WINDOWS_CUI
.corflags 0x00000001    //  ILONLY
// Image base: 0x081A0000


// =============== CLASS MEMBERS DECLARATION ===================

.class public auto ansi beforefieldinit Calc
       extends [mscorlib]System.Object
{
  .field family float64 sum
  .field family int32 n
  .method public hidebysig instance void 
          Add(float64 number) cil managed
  {
    // Code size       32 (0x20)
    .maxstack  3
    .locals init (int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldarg.0
    IL_0003:  ldfld      float64 Calc::sum
    IL_0008:  ldarg.1
    IL_0009:  add
    IL_000a:  stfld      float64 Calc::sum
    IL_000f:  ldarg.0
    IL_0010:  ldarg.0
    IL_0011:  ldfld      int32 Calc::n
    IL_0016:  stloc.0
    IL_0017:  ldloc.0
    IL_0018:  ldc.i4.2
    IL_0019:  add
    IL_001a:  stfld      int32 Calc::n
    IL_001f:  ret
  } // end of method Calc::Add

  .method public hidebysig instance float64 
          GetSum() cil managed
  {
    // Code size       12 (0xc)
    .maxstack  1
    .locals init (float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldfld      float64 Calc::sum
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Calc::GetSum

  .method public hidebysig specialname rtspecialname 
          instance void  .ctor() cil managed
  {
    // Code size       30 (0x1e)
    .maxstack  8
    IL_0000:  ldarg.0
    IL_0001:  ldc.r8     0.0
    IL_000a:  stfld      float64 Calc::sum
    IL_000f:  ldarg.0
    IL_0010:  ldc.i4.0
    IL_0011:  stfld      int32 Calc::n
    IL_0016:  ldarg.0
    IL_0017:  call       instance void [mscorlib]System.Object::.ctor()
    IL_001c:  nop
    IL_001d:  ret
  } // end of method Calc::.ctor

} // end of class Calc


// =============================================================

// *********** DISASSEMBLY COMPLETE ***********************
// Warnung: Win32-Ressourcendatei "D:\Studium\Code\SWK5\uebung01\Basic\calc.res" wurde erstellt.
