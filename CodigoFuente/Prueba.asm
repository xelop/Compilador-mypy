.MODEL small
.486
.STACK 1000h

.DATA

    chara db ''     ; es un char
    ttrue db 0    ; es un booleano
    ffalse db 0    ; es un booleano
    stringa db ""   ; es un string
    stringb db ""   ; es un string
    inta dw 0    ; es un entero
.CODE
start PROC
    MOV ax, @data
    MOV ds, ax


    MOV ax, 4c00h
    INT 21h

funcion2 proc near
    ;guarda registros
    ;cuerpo de la función:

    MOV ecx,9  ;Codigo de suma
    MOV eax,6
    ADD eax,ecx ; resultado en eax
    PUSH eax
    MOV ecx,9  ;Codigo de potencia
    SHL ecx,16
    SHR ecx,16 ;limpia ecx para que solo quede cx debido a un valor muy alto
    MOV eax,8
    SHL eax,16
    SHR eax,16 ;limpia eax para que solo quede ax debido a un valor muy alto
    sig:
    MUL ax 
    LOOP sig 
    ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    MOV ecx,9  ;Codigo de division
    POP eax
    DIV ecx ; resultado en eax
    PUSH ecx
    MOV ecx,8  ;Codigo de modulo
    POP eax
    DIV ecx ; resultado en edx
    PUSH edx
    POP ecx  ;Codigo de multiplicacion
    POP eax
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    MOV ecx,7  ;Codigo de multiplicacion
    MOV eax,6
    MUL cx ; resultado en dx:ax
    MOV cx, dx  ;move upper half(16 bits) of result in cx
    SHL ecx, 16 ;shift the contents of ecx 16 bits to the left
    MOV cx, ax  ;move lower half(16 bits) of result in cxpush ax
    PUSH ecx
    POP ecx  ;Codigo de resta
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
