function adicionaClasse(c) {
    document.getElementById("cartahover").src = "/LordOfFlorestal/javax.faces.resource/" + c + ".xhtml?ln=cartas";
    document.getElementById("cartahover").classList.add("visible");
}
function removeClasse() {
    document.getElementById("cartahover").classList.remove("visible");
}