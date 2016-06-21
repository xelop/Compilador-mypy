.MODEL small
.486
.STACK 1000h

.DATA

    a dw 0    ; es un entero
.CODE
start PROC
    MOV ax, @data
    MOV ds, ax

    MOV ax,0
    CMP ax,0
    JNZ elseLabel0 ; ahora viene codigo de if
    MOV ecx,2
    MOV eax,2
    ADD eax,ecx ; resultado en eax
    PUSH eax
    POP eax  ;FIN EXPRESION 
    MOV ax,0
    CMP ax,0
    JNZ elseLabel2 ; ahora viene codigo de if
    MOV ecx,3
    MOV eax,3
    ADD eax,ecx ; resultado en eax
    PUSH eax
    POP eax  ;FIN EXPRESION 
    JMP exitLabel3
    elseLabel2: ; no hubo else, va vacío
    exitLabel3: ;termina bloque if-else

    JMP exitLabel1
    elseLabel0: ; ahora viene codigo de else
    MOV ecx,4
    MOV eax,4
    ADD eax,ecx ; resultado en eax
    PUSH eax
    POP eax  ;FIN EXPRESION 
    exitLabel1: ;termina bloque if-else





    MOV ax, 4c00h
    INT 21h

funcion2 proc near
    ;guarda registros
    ;cuerpo de la función:


    ;saca registros
funcion2 endp

funcion1 proc near
    ;guarda registros
    ;cuerpo de la función:


    ;saca registros
funcion1 endp

start ENDP
end start
