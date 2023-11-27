package Entidad;

public class CARRITO {
    private int ID_USUARIO ,Valor_producto,ID_producto;
    private String nombre_producto , Des_producto , Disponible , img ;

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public int getValor_producto() {
        return Valor_producto;
    }

    public void setValor_producto(int valor_producto) {
        Valor_producto = valor_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDes_producto() {
        return Des_producto;
    }

    public void setDes_producto(String des_producto) {
        Des_producto = des_producto;
    }

    public String getDisponible() {
        return Disponible;
    }

    public void setDisponible(String disponible) {
        Disponible = disponible;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getID_producto() {
        return ID_producto;
    }

    public void setID_producto(int ID_producto) {
        this.ID_producto = ID_producto;
    }
}
