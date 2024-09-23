<template>
  <div id="numero">
    <input v-model="numero" type="text" id="numero" size="10">
    <button @click="enviarNumero()">Enviar</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      numero: 0,
      datos: []
    }
  },
  methods: {
    enviarNumero() {
      const num = new FormData();
      num.append("num", this.numero);
      console.log(num)
      fetch(`http://localhost:3000/getPreguntes`, {
        method: "POST",
        body: num
      })
      .then(res => res.json())
      .then((data) => {
        this.datos = data;
        console.log(this.datos);
      });
    }
  }
}
</script>