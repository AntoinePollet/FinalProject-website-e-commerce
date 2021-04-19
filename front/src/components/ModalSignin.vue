<template>
	<v-card>
		<h2 class="pt-5">Bonjour !</h2>
		<h4>Connectez vous à votre compte ici.</h4>
		<v-form ref="form" v-model="validForm" class="pa-5">
			<v-text-field
				prepend-inner-icon="mdi-account"
				placeholder="username"
				v-model="username"
				:rules="usernameRule"
				outlined
			/>
			<v-text-field
				prepend-inner-icon="mdi-lock"
				v-model="password"
				placeholder="mot de passe"
				type="password"
				:rules="passwordRule"
				outlined
			/>
			<v-checkbox
				class="mt-0"
				label="se souvenir de moi"
				v-model="rememberMe"
			/>
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
	name: 'SignInModal',
	data() {
		return {
			validForm: true,
			username: '',
			password: '',
			rememberMe: false,
			goTo: '/',
			usernameRule: [v => !!v || 'username requis !'],
			passwordRule: [
				v => !!v || 'Password requis !',

				v =>
					/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$/.test(v) ||
					'Le mot de passe doit contenir une lettre majuscule et au moins un chiffre !',
				v =>
					(v && v.length >= 6) ||
					'Le mot de passe doit contenir au minimum 6 caractères'
			]
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
						password: this.password,
						saveInfos: this.rememberMe
					};
					await this.$store.dispatch('user/signin', payload);
					await this.$store.dispatch(
						'user/getCommands',
						this.$store.state.user.username
					);
					this.$emit('closeSignin');
				}
			} catch (error) {
				this.$snotify.error('Username ou mdp invalide');
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