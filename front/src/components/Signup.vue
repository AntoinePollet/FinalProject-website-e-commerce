<template>
	<v-card>
		<h2 class="pt-5">S'inscrire</h2>
		<v-form ref="form" v-model="valid" class="pa-5">
			<v-text-field
				prepend-inner-icon="mdi-account"
				placeholder="full name"
				v-model="fullName"
				:rules="fullNameRule"
				outlined
			/>
			<v-text-field
				prepend-inner-icon="mdi-email"
				placeholder="email"
				v-model="email"
				:rules="emailRule"
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
			<v-text-field
				prepend-inner-icon="mdi-lock"
				v-model="passwordAgain"
				placeholder="Retaper votre mot de passe"
				type="password"
				:rules="passwordAgainRule"
				outlined
			/>
			<v-card-actions class="pa-0"
				><v-btn class="teal white--text" width="100%" @click="signup"
					>Sign up</v-btn
				></v-card-actions
			>
			<v-divider class="my-5"></v-divider>
			<p @click="$emit('signin')">
				vous avez déjà un compte ?
				<span class="word">Se connecter</span>
			</p>
		</v-form>
	</v-card>
</template>

<script>
export default {
	name: 'Signup',
	data() {
		return {
			valid: true,
			email: '',
			goTo: '/',
			password: '',
			passwordAgain: '',
			fullName: '',
			fullNameRule: [v => !!v || 'Prénom requis'],
			emailRule: [v => !!v || 'Email requis !'],
			passwordRule: [
				v => !!v || 'Password requis !',

				v =>
					/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$/.test(v) ||
					'Le mot de passe doit contenir une lettre majuscule et au moins un chiffre !',
				v =>
					(v && v.length >= 6) ||
					'Le mot de passe doit contenir au minimum 6 caractères'
			],
			passwordAgainRule: [v => !!v || 'Password requis !']
		};
	},
	beforeRouteEnter(to, from, next) {
		if (from.name === 'livraison') {
			next(vm => {
				vm.goTo = '/commande/livraison';
			});
		}
		if (from.name === 'profil') {
			next(vm => {
				vm.goTo = '/profil';
			});
		}
		next();
	},
	methods: {
		samePassword() {
			return this.password === this.passwordAgain;
		},
		async signup() {
			try {
				if (this.$refs.form.validate() && this.samePassword()) {
					console.log("true")
					const payload = {
						username: this.fullName,
						email: this.email,
						password: this.password,
						role: ['user']
					};
					await this.$store.dispatch('user/signup', payload);
					this.$refs.form.reset();
				} 
			} catch (error) {
				this.$snotify.error('erreur lors de la création du compte');
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