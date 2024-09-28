package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Material;

public class Caderno extends Produto{

    private Material material;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String toString() {
        return "Caderno{" +
                '\'' +
                ", material=" + material + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }

    public double CalcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + material.getFator());
    }
}
