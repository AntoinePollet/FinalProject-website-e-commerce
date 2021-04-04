<template>
	<v-card>
		<h2 class="pt-5">Bonjour !</h2>
		<h4>Connectez vous à votre compte ici.</h4>
		<v-form ref="form" v-model="validForm" class="pa-5">
			<v-text-field
				prepend-inner-icon="mdi-email"
				placeholder="email"
				v-model="username"
				:rules="emailRule"
				outlined
			></v-text-field>
			<v-text-field
				prepend-inner-icon="mdi-lock"
				v-model="password"
				placeholder="mot de passe"
				type="password"
				:rules="passwordRule"
				outlined
			></v-text-field>
			<v-card-actions class="pa-0"
				><v-btn class="teal white--text" width="100%" @click="signin"
					>Login</v-btn
				></v-card-actions
			>
			<v-divider class="my-5"></v-divider>
			<p>Mot de passe oublié? <span class="word">Reset</span></p>
			<p>
				Vous n'avez pas de compte?
				<span @click="$emit('signup')" class="word">Se créer un compte</span>
			</p>
		</v-form>
	</v-card>
</template>

<script>
export default {
	name: 'Login',
	data() {
		return {
			validForm: true,
			username: '',
			email: '',
			password: '',
			goTo: '/',
			emailRule: [v => !!v || 'Email requis !'],
			passwordRule: [v => !!v || 'Password requis !']
		};
	},
	beforeRouteEnter(to, from, next) {
		if (from.name === 'livraison') {
			next(vm => {
				vm.goTo = '/commande/livraison';
			});
		}
		next();
	},
	methods: {
		async signin() {
			try {
				if (this.$refs.form.validate()) {
					const payload = {
						username: this.username,
						password: this.password
					};
					await this.$store.dispatch('user/signin', payload);
					this.$emit('closeSignin');
				}
			} catch (error) {
				console.log(error);
			}
		}
	}
};
</script>

<style lang="scss" scoped>
.word {
	color: #0077b6;
	font-weight: bold;
	cursor: pointer;
}
</style>