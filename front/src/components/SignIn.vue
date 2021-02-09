<template>
	<v-main>
		<div class="d-flex justify-center">
			<v-card>
				<h2 class="pt-5">S'inscrire</h2>
				<v-form ref="form" v-model="valid" class="pa-5">
					<v-row class="ma-0 justify-space-between">
						<v-text-field
							class="mr-2"
							placeholder="Prénom"
							v-model="firstName"
							:rules="firstNameRule"
							outlined
						/>
						<v-text-field
							class="ml-2"
							placeholder="Nom"
							v-model="lastName"
							:rules="lastNameRule"
							outlined
						/>
					</v-row>
					<v-text-field
						placeholder="email"
						v-model="email"
						:rules="emailRule"
						outlined
					/>
					<v-text-field
						placeholder="mot de passe"
						type="password"
						:rules="passwordRule"
						outlined
					/>
					<v-card-actions class="justify-end"
						><v-btn class="teal white--text" @click="signIn"
							>Sign in</v-btn
						></v-card-actions
					>

					<v-dialog v-model="dialog" width="500">
						<template v-slot:activator="{ on, attrs }">
							<p>
								vous avez déjà un compte ?
								<span
									@click="login"
									class="text-decoration-underline"
									v-bind="attrs"
									v-on="on"
									>Login</span
								>
							</p>
						</template>
						<Login />
					</v-dialog>
				</v-form>
			</v-card>
		</div>
	</v-main>
</template>

<script>
import Login from "./Login.vue"
export default {
	name: 'SignIn',
	components: { Login },
	data() {
		return {
			valid: true,
			dialog: false,
			email: '',
			goTo: '/',
			firstName: '',
			firstNameRule: [v => !!v || 'Prénom requis'],
			lastName: '',
			lastNameRule: [v => !!v || 'Nom requis'],
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
			})
		}
		next();
	},
	methods: {
		signIn() {
			if (this.$refs.form.validate()) {
				this.$store.dispatch('user/login');
				this.$router.push(this.goTo);
			}
		},
		login() {
			this.dialog = true
		}
	}
};
</script>

<style lang="scss" scoped>
</style>