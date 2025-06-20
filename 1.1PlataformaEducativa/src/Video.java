class Video extends MaterialCurso {
    private int duracionMin;

    public Video(String titulo, String autor, int duracionMin) {
        super(titulo, autor);
        this.duracionMin = duracionMin;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracionMin + " min");
    }
}