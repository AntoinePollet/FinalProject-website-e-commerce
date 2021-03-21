<template>
	<div class="navigation">
		<ProfilNavigation />
		<v-container class="pa-15 col-6">
			<!-- 
			<v-breadcrumbs :items="items">
				<template v-slot:item="{ item }">
					<v-breadcrumbs-item :to="item.href" :exact="true">{{
						item.text
					}}</v-breadcrumbs-item>
				</template>
			</v-breadcrumbs>
			-->
			<h2 class="mb-3 title-info">Mes informations</h2>
			<v-form ref="form" v-model="valid" :lazy-validation="true">
				<p class="text-left">Nom</p>
				<v-text-field
					v-model="name"
					hint="Facultatif"
					:rules="nameRule"
					persistent-hint
					outlined
				/>
				<p class="text-left">Prenom</p>
				<div class="d-flex">
					<v-text-field
						v-model="prenom"
						hint="Facultatif"
						:rules="lastNameRule"
						persistent-hint
						outlined
					/>
					<div class="d-flex">
						<v-checkbox
							label="Mr"
							v-model="civiliteMr"
							:disabled="civiliteMme"
							class="ml-5"
						/>
						<v-checkbox
							label="Mme"
							v-model="civiliteMme"
							:disabled="civiliteMr"
							class="ml-5"
						/>
					</div>
				</div>
				<p class="text-left">Email</p>
				<v-text-field
					type="email"
					v-model="email"
					:rules="emailRule"
					hint="Obligatoire"
					required
					persistent-hint
					outlined
				/>
				<p class="text-left">Code postal</p>
				<v-text-field
					hint="Facultatif"
					persistent-hint
					v-model="postalCode"
					outlined
				/>
				<v-card-actions class="d-flex justify-end"
					><v-btn color="teal white--text" @click="send()" :disabled="!valid"
						>Enregistrer</v-btn
					></v-card-actions
				>
			</v-form>
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