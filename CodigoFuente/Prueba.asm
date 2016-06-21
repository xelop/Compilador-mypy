.MODEL small
.486
.STACK 1000h

.DATA

    a dw 0    ; es un entero
.CODE
start PROC
mov ax, @data
mov ds, ax

mov ecx,8
mov eax,6
add eax,ecx ; resultado en eax
 push eax
pop eax  ;FIN EXPRESION 
    MOV ax,0
    CMP ax,0
    JNZ elseLabel0 ; ahora viene codigo de if
    JMP exitLabel1
    elseLabel0: ; ahora viene codigo de else
    exitLabel1: ;termina bloque if-else



    MOV ax, 4c00h
    INT 21h

funcion2 proc near
    ;guarda registros
    ;cuerpo de la función:

mov ecx,9
mov eax,6
add eax,ecx ; resultado en eax
 push eax
mov ecx,9
mov eax,8
mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
mov ecx,9
pop eax
 mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
mov ecx,8
pop eax
 mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
pop ecx
 pop eax
 mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
mov ecx,7
mov eax,6
mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
pop ecx
 pop eax
 sub eax,ecx ; resultado en eax
 push eax
pop eax  ;FIN EXPRESION 

    ;saca registros
funcion2 endp

funcion1 proc near
    ;guarda registros
    ;cuerpo de la función:

mov ecx,9
mov eax,6
add eax,ecx ; resultado en eax
 push eax
mov ecx,9
mov eax,8
mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
mov ecx,9
pop eax
 mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
mov ecx,8
pop eax
 mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
pop ecx
 pop eax
 mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
mov ecx,7
mov eax,6
mul cx ; resultado en dx:ax
 mov cx, dx  ;move upper half(16 bits) of result in cx
shl ecx, 16 ;shift the contents of ecx 16 bits to the left
mov cx, ax  ;move lower half(16 bits) of result in cxpush ax
push ecx
pop ecx
 pop eax
 sub eax,ecx ; resultado en eax
 push eax
pop eax  ;FIN EXPRESION 

    ;saca registros
funcion1 endp

start ENDP
end start
