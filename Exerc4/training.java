public class training {

    public static void main(String[] args) {
        // Dados de treinamento
        double[] x = {1.0, 2.0, 3.0, 4.0, 5.0}; // Variável independente (dados de entrada)
        double[] y = {2.0, 4.0, 5.5, 4.8, 6.0}; // Variável dependente (saída desejada)

        // Calcular os coeficientes da regressão linear
        double[] coeficientes = calcularRegressaoLinear(x, y);

        // Imprimir os coeficientes
        System.out.println("Coeficiente angular (a): " + coeficientes[0]);
        System.out.println("Coeficiente linear (b): " + coeficientes[1]);

        // Usar o modelo para fazer previsões
        double valorX = 6.0; // Valor de entrada para fazer uma previsão
        double previsao = fazerPrevisao(coeficientes, valorX);
        System.out.println("Previsão para x=" + valorX + ": " + previsao);
    }

    // Função para calcular os coeficientes da regressão linear
    public static double[] calcularRegressaoLinear(double[] x, double[] y) {
        int n = x.length;
        double somaX = 0.0, somaY = 0.0, somaXY = 0.0, somaXQuadrado = 0.0;

        for (int i = 0; i < n; i++) {
            somaX += x[i];
            somaY += y[i];
            somaXY += x[i] * y[i];
            somaXQuadrado += x[i] * x[i];
        }

        double coeficienteAngular = (n * somaXY - somaX * somaY) / (n * somaXQuadrado - somaX * somaX);
        double coeficienteLinear = (somaY - coeficienteAngular * somaX) / n;

        double[] coeficientes = {coeficienteAngular, coeficienteLinear};
        return coeficientes;
    }

    // Função para fazer previsões com base nos coeficientes calculados
    public static double fazerPrevisao(double[] coeficientes, double valorX) {
        double coeficienteAngular = coeficientes[0];
        double coeficienteLinear = coeficientes[1];
        double previsao = coeficienteAngular * valorX + coeficienteLinear;
        return previsao;
    }
}
