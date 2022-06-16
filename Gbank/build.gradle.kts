import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	id("jacoco")
}

group = "com.giovana.simoes"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

jacoco {
	toolVersion = "0.8.8"
}
repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

val kluentVersion = "1.68"
val mockkVersion = "2.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("com.ninja-squad:springmockk:$mockkVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
}

tasks.test {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		html.outputLocation.dir("${buildDir}/reports/jacoco.html")
		xml.required.set(false)
		html.required.set(true)
		csv.required.set(false)
	}
	group = "Reporting"
	description = "Generate Jacoco coverage reports"

	finalizedBy(tasks.jacocoTestCoverageVerification)  //run right after jacocoTestReport
}
tasks.jacocoTestCoverageVerification {   //Verifies the code coverage rule if enabled.
	violationRules {
		rule {
			enabled = true
			limit {
				minimum = 0.1.toBigDecimal()
			}

			rule {
				isEnabled = true
				element = "CLASS"
				includes = listOf("org.gradle.*")

				limit {
					counter = "LINE"
					value = "TOTALCOUNT"
				}
			}
		}
	}
}