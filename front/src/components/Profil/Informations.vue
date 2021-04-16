<template>
	<div class="navigation">
		<ProfilNavigation />
		<v-container class="container col-5 mt-8">
			<v-card class="px-5" flat>
				<h2 class="mb-3 pt-5 title-info">Mes informations</h2>
				<v-form ref="form" v-model="valid" :lazy-validation="true">
					<p class="text-left mb-1">Nom</p>
					<v-text-field v-model="name" :rules="nameRule" outlined dense />
					<p class="text-left mb-1">Prenom</p>
					<div class="d-flex">
						<v-text-field
							v-model="prenom"
							:rules="lastNameRule"
							outlined
							dense
						/>
						<div class="d-flex">
							<v-checkbox
								label="Mr"
								v-model="civiliteMr"
								:disabled="civiliteMme"
								class="ml-5 mt-0"
							/>
							<v-checkbox
								label="Mme"
								v-model="civiliteMme"
								:disabled="civiliteMr"
								class="ml-5 mt-0"
							/>
						</div>
					</div>
					<p class="text-left mb-1">Email</p>
					<v-text-field
						type="email"
						v-model="email"
						:rules="emailRule"
						required
						outlined
						dense
					/>
					<p class="text-left mb-1">Code postal</p>
					<v-text-field v-model="postalCode" outlined dense />
					<v-card-actions class="d-flex justify-end"
						><v-btn
							color="pink lighten-2 white--text"
							@click="send()"
							:disabled="!valid"
							>Enregistrer</v-btn
						></v-card-actions
					>
				</v-form>
			</v-card>
		</v-container>
	</div>
</template>

<script>
import ProfilNavigation from './ProfilNavigation.vue';
export default {
	name: 'Informations',
	components: { ProfilNavigation },
	data() {
		return {
			valid: true,
			name: '',
			prenom: '',
			email: '',
			civiliteMr: false,
			civiliteMme: false,
			postalCode: '',
			nameRule: [
				v => !!v || 'Name is required',
				v => /^[a-zA-Z]+$/.test(v) || 'Name incorrect'
			],
			lastNameRule: [
				v => !!v || 'Last name is required',
				v => /^[a-zA-Z]+$/.test(v) || 'Prénom incorrect'
			],
			emailRule: [
				v => !!v || 'Email is required',
				v =>
					/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(v) ||
					'format email is not valid'
			]
		};
	},
	computed: {
		path() {
			return this.$route.path;
		}
	},
	methods: {
		send() {
			this.$refs.form.validate();
		}
	}
};
</script>

<style lang="scss" scoped>
.title-info {
	color: rgb(0, 0, 0, 0.87);
}
</style>