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

    MOV ecx,9
    MOV eax,6
    ADD eax,ecx ; resultado en eax
    PUSH eax
    MOV ecx,9
    MOV eax,8
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    MOV ecx,9
    POP eax
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    MOV ecx,8
    POP eax
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    POP ecx
    POP eax
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    MOV ecx,7
    MOV eax,6
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    POP ecx
    POP eax
    SUB eax,ecx ; resultado en eax
    PUSH eax
    POP eax  ;FIN EXPRESION 

    ;saca registros
funcion2 endp

funcion1 proc near
    ;guarda registros
    ;cuerpo de la función:


    ;saca registros
funcion1 endp

start ENDP
end start
