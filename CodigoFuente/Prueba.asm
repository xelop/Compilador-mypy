pila segment stack 'stack'
    dw 256 dup (?)
pila ends

datos segment

datos ends

codigo segment
    assume cs:codigo, ds:datos, ss:pila

inicio:
    MOV ax, datos
    MOV ds, ax


    MOV ax, 4c00h
    INT 21h

codigo ends
end inicio
