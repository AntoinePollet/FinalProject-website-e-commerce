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
				placeholder="mot de passe"
				type="password"
				:rules="passwordRule"
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
			fullName: '',
			fullNameRule: [v => !!v || 'Prénom requis'],
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
		if (from.name === 'profil') {
			next(vm => {
				vm.goTo = '/profil';
			});
		}
		next();
	},
	methods: {
		signup() {
			if (this.$refs.form.validate()) {
				this.$store.dispatch('user/login');
				this.$router.push(this.goTo);
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