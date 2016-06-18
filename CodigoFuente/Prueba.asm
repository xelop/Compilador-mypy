pila segment stack 'stack'
    dw 256 dup (?)
pila ends

datos segment

datos ends
    bool1 db 0    ; es un booleano
    bool2 db 0    ; es un booleano
    flotante dq 0.0 ; es un float
    charv db ''     ; es un char
    enterox dw 0    ; es un entero
    enteroy dw 0    ; es un entero
    stringa db ""   ; es un string

codigo segment
    assume cs:codigo, ds:datos, ss:pila

inicio
    mov ax, datos
    mov ds, ax

    mov ax, 4c00h
    int 21h

funcion2 proc near
    pusha
    ;cuerpo de la función
    popa
funcion2 endp

funcion1 proc near
    pusha
    ;cuerpo de la función
    popa
funcion1 endp

codigo ends
end inicio
