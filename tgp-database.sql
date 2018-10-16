/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100110
Source Host           : localhost:3306
Source Database       : tgp

Target Server Type    : MYSQL
Target Server Version : 100110
File Encoding         : 65001

Date: 2017-06-08 00:20:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for arma
-- ----------------------------
DROP TABLE IF EXISTS `arma`;
CREATE TABLE `arma` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o2g9j1o1fst3199hejxh5yedn` (`mundo_id`),
  CONSTRAINT `FK_o2g9j1o1fst3199hejxh5yedn` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of arma
-- ----------------------------
INSERT INTO `arma` VALUES ('1', 'Representa um recurso disponível ao jogador (máquina virtual\r\n)', 'Servidor', '1');
INSERT INTO `arma` VALUES ('2', 'Carro ou moto', 'Transporte', '1');
INSERT INTO `arma` VALUES ('3', 'Chave de fenda, estilete, multímetro...', 'Ferramentas', '1');
INSERT INTO `arma` VALUES ('4', 'Capacidade de entrar em qualquer ambiente do mundo', 'Acesso Irrestrito', '1');
INSERT INTO `arma` VALUES ('5', 'Capacidade de decidir e priorizar suas próprias aventuras', 'Autonomia', '1');

-- ----------------------------
-- Table structure for armadura
-- ----------------------------
DROP TABLE IF EXISTS `armadura`;
CREATE TABLE `armadura` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h7kj8myhx048bwnihiotcs2h3` (`mundo_id`),
  CONSTRAINT `FK_h7kj8myhx048bwnihiotcs2h3` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of armadura
-- ----------------------------
INSERT INTO `armadura` VALUES ('1', 'Representa que o jogador não pode sair das aventuras por um longo período de tempo', 'Estabilidade', '1');

-- ----------------------------
-- Table structure for avaliacao
-- ----------------------------
DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE `avaliacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of avaliacao
-- ----------------------------

-- ----------------------------
-- Table structure for aventura
-- ----------------------------
DROP TABLE IF EXISTS `aventura`;
CREATE TABLE `aventura` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agilidade_necessaria` double NOT NULL,
  `carisma_necessario` double NOT NULL,
  `constituicao_necessario` double NOT NULL,
  `data_criacao` date DEFAULT NULL,
  `data_finalizacao` date DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_ultima_alteracao` date DEFAULT NULL,
  `descricao` int(11) NOT NULL,
  `forca_necessaria` double NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `resistencia_necessaria` double NOT NULL,
  `sabedoria_necessaria` double NOT NULL,
  `xp` int(11) NOT NULL,
  `estado_id` bigint(20) DEFAULT NULL,
  `level_id` bigint(20) DEFAULT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  `personagem_criador_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_19uy2kow0bcvrcy4f342obmmn` (`estado_id`),
  KEY `FK_5mca7a8fg1h5e9cnrfr3wcajw` (`level_id`),
  KEY `FK_gf0xl5atcua8nx7cnftkuqkn9` (`mundo_id`),
  KEY `FK_tc80g10mnc1uuhcqjoc85bf6v` (`personagem_criador_id`),
  CONSTRAINT `FK_19uy2kow0bcvrcy4f342obmmn` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`),
  CONSTRAINT `FK_5mca7a8fg1h5e9cnrfr3wcajw` FOREIGN KEY (`level_id`) REFERENCES `level` (`id`),
  CONSTRAINT `FK_gf0xl5atcua8nx7cnftkuqkn9` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id`),
  CONSTRAINT `FK_tc80g10mnc1uuhcqjoc85bf6v` FOREIGN KEY (`personagem_criador_id`) REFERENCES `personagem` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura
-- ----------------------------

-- ----------------------------
-- Table structure for aventura_arma
-- ----------------------------
DROP TABLE IF EXISTS `aventura_arma`;
CREATE TABLE `aventura_arma` (
  `Aventura_id` bigint(20) NOT NULL,
  `armas_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_i8noobeffximfco8ss8jq2yag` (`armas_id`),
  KEY `FK_i8noobeffximfco8ss8jq2yag` (`armas_id`),
  KEY `FK_2xrmn9ubsx98wsj073sqavxff` (`Aventura_id`),
  CONSTRAINT `FK_2xrmn9ubsx98wsj073sqavxff` FOREIGN KEY (`Aventura_id`) REFERENCES `aventura` (`id`),
  CONSTRAINT `FK_i8noobeffximfco8ss8jq2yag` FOREIGN KEY (`armas_id`) REFERENCES `arma` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura_arma
-- ----------------------------

-- ----------------------------
-- Table structure for aventura_classe
-- ----------------------------
DROP TABLE IF EXISTS `aventura_classe`;
CREATE TABLE `aventura_classe` (
  `Aventura_id` bigint(20) NOT NULL,
  `classes_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8ffjkf3sp81versckyy1r1rhx` (`classes_id`),
  KEY `FK_8ffjkf3sp81versckyy1r1rhx` (`classes_id`),
  KEY `FK_grlld6kouyfdetxm8x7o5qdv0` (`Aventura_id`),
  CONSTRAINT `FK_8ffjkf3sp81versckyy1r1rhx` FOREIGN KEY (`classes_id`) REFERENCES `classe` (`id`),
  CONSTRAINT `FK_grlld6kouyfdetxm8x7o5qdv0` FOREIGN KEY (`Aventura_id`) REFERENCES `aventura` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura_classe
-- ----------------------------

-- ----------------------------
-- Table structure for aventura_noplayablechar
-- ----------------------------
DROP TABLE IF EXISTS `aventura_noplayablechar`;
CREATE TABLE `aventura_noplayablechar` (
  `Aventura_id` bigint(20) NOT NULL,
  `npcs_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_suhabigumxyi9iqaw0c973br0` (`npcs_id`),
  KEY `FK_suhabigumxyi9iqaw0c973br0` (`npcs_id`),
  KEY `FK_49lctsu9pn4irkt6r7crnn6rc` (`Aventura_id`),
  CONSTRAINT `FK_49lctsu9pn4irkt6r7crnn6rc` FOREIGN KEY (`Aventura_id`) REFERENCES `aventura` (`id`),
  CONSTRAINT `FK_suhabigumxyi9iqaw0c973br0` FOREIGN KEY (`npcs_id`) REFERENCES `noplayablechar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura_noplayablechar
-- ----------------------------

-- ----------------------------
-- Table structure for aventura_pericia
-- ----------------------------
DROP TABLE IF EXISTS `aventura_pericia`;
CREATE TABLE `aventura_pericia` (
  `Aventura_id` bigint(20) NOT NULL,
  `pericias_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8yx9crk29d2maq7edd2uawats` (`pericias_id`),
  KEY `FK_8yx9crk29d2maq7edd2uawats` (`pericias_id`),
  KEY `FK_6e7346n17hdev6ia180sinm8v` (`Aventura_id`),
  CONSTRAINT `FK_6e7346n17hdev6ia180sinm8v` FOREIGN KEY (`Aventura_id`) REFERENCES `aventura` (`id`),
  CONSTRAINT `FK_8yx9crk29d2maq7edd2uawats` FOREIGN KEY (`pericias_id`) REFERENCES `pericia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura_pericia
-- ----------------------------

-- ----------------------------
-- Table structure for aventura_raca
-- ----------------------------
DROP TABLE IF EXISTS `aventura_raca`;
CREATE TABLE `aventura_raca` (
  `Aventura_id` bigint(20) NOT NULL,
  `racas_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_5ye6ca3vto6gduucuhn83sr1d` (`racas_id`),
  KEY `FK_5ye6ca3vto6gduucuhn83sr1d` (`racas_id`),
  KEY `FK_3uo3pw1w4ccipi056sthv1ovj` (`Aventura_id`),
  CONSTRAINT `FK_3uo3pw1w4ccipi056sthv1ovj` FOREIGN KEY (`Aventura_id`) REFERENCES `aventura` (`id`),
  CONSTRAINT `FK_5ye6ca3vto6gduucuhn83sr1d` FOREIGN KEY (`racas_id`) REFERENCES `raca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura_raca
-- ----------------------------

-- ----------------------------
-- Table structure for aventura_tesouro
-- ----------------------------
DROP TABLE IF EXISTS `aventura_tesouro`;
CREATE TABLE `aventura_tesouro` (
  `Aventura_id` bigint(20) NOT NULL,
  `tesouros_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_mxuqrky53mvr33wkt6ggkps3e` (`tesouros_id`),
  KEY `FK_mxuqrky53mvr33wkt6ggkps3e` (`tesouros_id`),
  KEY `FK_cvuwiu5xa12cgk43mfw2mnln7` (`Aventura_id`),
  CONSTRAINT `FK_cvuwiu5xa12cgk43mfw2mnln7` FOREIGN KEY (`Aventura_id`) REFERENCES `aventura` (`id`),
  CONSTRAINT `FK_mxuqrky53mvr33wkt6ggkps3e` FOREIGN KEY (`tesouros_id`) REFERENCES `tesouro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of aventura_tesouro
-- ----------------------------

-- ----------------------------
-- Table structure for batalha
-- ----------------------------
DROP TABLE IF EXISTS `batalha`;
CREATE TABLE `batalha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` date DEFAULT NULL,
  `data_finalizacao` date DEFAULT NULL,
  `data_ultima_alteracao` date DEFAULT NULL,
  `pontos_de_balalha` int(11) NOT NULL,
  `aventura_id` bigint(20) DEFAULT NULL,
  `estado_id` bigint(20) DEFAULT NULL,
  `personagem_criador_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_db4dabsioc6ilfb76xv7og786` (`aventura_id`),
  KEY `FK_6skeuqnnk7lsjokwrn9l6404j` (`estado_id`),
  KEY `FK_692n3ygaybny4pokc27hkmw14` (`personagem_criador_id`),
  CONSTRAINT `FK_692n3ygaybny4pokc27hkmw14` FOREIGN KEY (`personagem_criador_id`) REFERENCES `personagem` (`id`),
  CONSTRAINT `FK_6skeuqnnk7lsjokwrn9l6404j` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`),
  CONSTRAINT `FK_db4dabsioc6ilfb76xv7og786` FOREIGN KEY (`aventura_id`) REFERENCES `aventura` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of batalha
-- ----------------------------

-- ----------------------------
-- Table structure for classe
-- ----------------------------
DROP TABLE IF EXISTS `classe`;
CREATE TABLE `classe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `valor_padrao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of classe
-- ----------------------------
INSERT INTO `classe` VALUES ('1', '1', 'Auxiliar\r\n');
INSERT INTO `classe` VALUES ('2', '2', 'Assistente C\r\n');
INSERT INTO `classe` VALUES ('3', '3', 'Assistente B\r\n');
INSERT INTO `classe` VALUES ('4', '4', 'Assistente A\r\n');
INSERT INTO `classe` VALUES ('5', '5', 'Técnico C\r\n');
INSERT INTO `classe` VALUES ('6', '6', 'Técnico B\r\n');
INSERT INTO `classe` VALUES ('7', '7', 'Técnico A\r\n');
INSERT INTO `classe` VALUES ('8', '8', 'Analista Jr\r\n');
INSERT INTO `classe` VALUES ('9', '9', 'Analista Pleno\r\n');
INSERT INTO `classe` VALUES ('10', '10', 'Analista Sênior\r\n');
INSERT INTO `classe` VALUES ('11', '10', 'Coordenador\r\n');
INSERT INTO `classe` VALUES ('12', '10', 'Gerente\r\n');
INSERT INTO `classe` VALUES ('13', '10', 'Diretor\r\n');

-- ----------------------------
-- Table structure for estado
-- ----------------------------
DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of estado
-- ----------------------------
INSERT INTO `estado` VALUES ('1', 'Aventura que está esperando para ser feita', 'ATIVA');
INSERT INTO `estado` VALUES ('2', 'Aventura que foi congelada para ser concluída em outra oportunidade', 'INATIVA');
INSERT INTO `estado` VALUES ('3', 'Aventura cancelada', 'CANCELADA');
INSERT INTO `estado` VALUES ('4', 'Aventura concluída com sucesso', 'CONCLUÍDA');
INSERT INTO `estado` VALUES ('5', 'Aventura concluída fora do prazo  (possui algumas batalhas a concluir também)', 'FINALIZADA');
INSERT INTO `estado` VALUES ('6', 'Aventura que ainda não foi concluída ou finalizada', 'INCOMPLETA');

-- ----------------------------
-- Table structure for estado_personagem
-- ----------------------------
DROP TABLE IF EXISTS `estado_personagem`;
CREATE TABLE `estado_personagem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of estado_personagem
-- ----------------------------
INSERT INTO `estado_personagem` VALUES ('1', 'Jogador apto a entrar em aventuras', 'ATIVO');
INSERT INTO `estado_personagem` VALUES ('2', 'Jogador que não pode participar das aventuras', 'INATIVO');
INSERT INTO `estado_personagem` VALUES ('3', 'Jogador com pontos de vida zerados', 'MORTO');

-- ----------------------------
-- Table structure for indicador
-- ----------------------------
DROP TABLE IF EXISTS `indicador`;
CREATE TABLE `indicador` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `importancia_do_indicador` varchar(255) DEFAULT NULL,
  `metas_curto_prazo` varchar(255) DEFAULT NULL,
  `metas_longo_prazo` varchar(255) DEFAULT NULL,
  `metodo_de_analise` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nome_chefe_imediato` varchar(255) DEFAULT NULL,
  `setor_de_coleta` varchar(255) DEFAULT NULL,
  `unidade_indicador` varchar(255) DEFAULT NULL,
  `responsavel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bf5jxrh8xsvkkaxlg9tb98dts` (`responsavel_id`),
  CONSTRAINT `FK_bf5jxrh8xsvkkaxlg9tb98dts` FOREIGN KEY (`responsavel_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of indicador
-- ----------------------------

-- ----------------------------
-- Table structure for indicador_avaliacao
-- ----------------------------
DROP TABLE IF EXISTS `indicador_avaliacao`;
CREATE TABLE `indicador_avaliacao` (
  `Indicador_id` bigint(20) NOT NULL,
  `avaliacoes_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_seu44gd2qf7w5jyv4mdfdsx1e` (`avaliacoes_id`),
  KEY `FK_seu44gd2qf7w5jyv4mdfdsx1e` (`avaliacoes_id`),
  KEY `FK_ividt0ye35sruritrklgk9coq` (`Indicador_id`),
  CONSTRAINT `FK_ividt0ye35sruritrklgk9coq` FOREIGN KEY (`Indicador_id`) REFERENCES `indicador` (`id`),
  CONSTRAINT `FK_seu44gd2qf7w5jyv4mdfdsx1e` FOREIGN KEY (`avaliacoes_id`) REFERENCES `avaliacao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of indicador_avaliacao
-- ----------------------------

-- ----------------------------
-- Table structure for level
-- ----------------------------
DROP TABLE IF EXISTS `level`;
CREATE TABLE `level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `fator_multiplicador_xp` double NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of level
-- ----------------------------
INSERT INTO `level` VALUES ('1', 'Aventuras fáceis dão metade do XP', '0.5', 'FÁCIL');
INSERT INTO `level` VALUES ('2', 'Aventuras normais dão o XP completo', '1', 'NORMAL');
INSERT INTO `level` VALUES ('3', 'Aventuras difíceis dão o dobro de XP', '2', 'DIFÍCIL');

-- ----------------------------
-- Table structure for mundo
-- ----------------------------
DROP TABLE IF EXISTS `mundo`;
CREATE TABLE `mundo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of mundo
-- ----------------------------
INSERT INTO `mundo` VALUES ('1', 'Setor de Tecnologia da Informação do Grupo JD', 'TI');
INSERT INTO `mundo` VALUES ('2', 'Setor Estatístico da Fazenda', 'Estatístico');
INSERT INTO `mundo` VALUES ('3', 'Setor de Coordenação da VDU', 'Coordenação VDU');

-- ----------------------------
-- Table structure for noplayablechar
-- ----------------------------
DROP TABLE IF EXISTS `noplayablechar`;
CREATE TABLE `noplayablechar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classe` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `raça` varchar(255) DEFAULT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_to5fk55873h8bbe1vg0j8xkb` (`mundo_id`),
  CONSTRAINT `FK_to5fk55873h8bbe1vg0j8xkb` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of noplayablechar
-- ----------------------------
INSERT INTO `noplayablechar` VALUES ('1', 'Supervisor', 'Ícaro Fernandes', 'Agrônomo', '2');
INSERT INTO `noplayablechar` VALUES ('2', 'Coordenador', 'João Victor', 'Agrônomo', '3');

-- ----------------------------
-- Table structure for perfil
-- ----------------------------
DROP TABLE IF EXISTS `perfil`;
CREATE TABLE `perfil` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of perfil
-- ----------------------------
INSERT INTO `perfil` VALUES ('1', 'Pode entrar em aventuras mas não pode concluí-las\r\n', 'Aprendiz\r\n');
INSERT INTO `perfil` VALUES ('2', 'Escolhe quais aventuras devem ser feitas e também os membros de sua equipe\r\n', 'Aventureiro\r\n');
INSERT INTO `perfil` VALUES ('3', 'Pode criar aventuras porém com baixo nível de XP e sem premiação\r\n', 'Campeão\r\n');
INSERT INTO `perfil` VALUES ('4', 'Perfil de Administrador de todas as aventuras, cria premios, define altos XPs, tem o poder de ressucitar\r\n', 'Mestre da Aventura\r\n');

-- ----------------------------
-- Table structure for pericia
-- ----------------------------
DROP TABLE IF EXISTS `pericia`;
CREATE TABLE `pericia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `mundo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ki22cf0furke9s0xmqiekr7t8` (`mundo_id`),
  CONSTRAINT `FK_ki22cf0furke9s0xmqiekr7t8` FOREIGN KEY (`mundo_id`) REFERENCES `mundo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pericia
-- ----------------------------
INSERT INTO `pericia` VALUES ('1', 'Capacidade de liderar conflitos', 'Gestão de Pessoas\r\n', '1');
INSERT INTO `pericia` VALUES ('2', 'Capacidade de planejar, executar e entregar projetos', 'Gestão de Projetos\r\n', '1');
INSERT INTO `pericia` VALUES ('3', 'Capacidade de trabalhar com conceitos da área de finanças', 'Financeiro\r\n', '1');
INSERT INTO `pericia` VALUES ('4', 'Capacidade de trabalhar com conceitos da área de contabilidade', 'Contabilidade\r\n', '1');
INSERT INTO `pericia` VALUES ('5', 'Capacidade de desenvolver aplicativos para dispositivos móveis', 'Programação Mobile\r\n', '1');
INSERT INTO `pericia` VALUES ('6', 'Capacidade de desenvolver softwares para a WEB (navegadores)', 'Programação Web\r\n', '1');
INSERT INTO `pericia` VALUES ('7', 'Capacidade de desenvolver softwares para ambientes Linux/Windows', 'Programação Desktop\r\n', '1');
INSERT INTO `pericia` VALUES ('8', 'Capacidade de trabalhar com a gestão de patrimônio', 'Gestão de Ativos\r\n', '1');
INSERT INTO `pericia` VALUES ('9', 'Capacidade de trabalhar com rede de computadores', 'Redes de Computadores\r\n', '1');
INSERT INTO `pericia` VALUES ('10', 'Capacidade de trabalhar com análise de dados ', 'Análise de dados\r\n', '1');
INSERT INTO `pericia` VALUES ('11', 'Capacidade de trabalhar com conceitos de treinamentos', 'Treinamento\r\n', '1');
INSERT INTO `pericia` VALUES ('12', 'Capacidade de trabalhar com estatísticas e dashboards', 'Indicadores', '1');
INSERT INTO `pericia` VALUES ('13', 'Capacidade de liderar equipes', 'Liderança\r\n', '1');

-- ----------------------------
-- Table structure for personagem
-- ----------------------------
DROP TABLE IF EXISTS `personagem`;
CREATE TABLE `personagem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agilidade` double NOT NULL,
  `carisma` double NOT NULL,
  `classe_de_armadura` int(11) NOT NULL,
  `constituicao` double NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `forca` double NOT NULL,
  `iniciativa` int(11) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  `pontos_de_batalha` int(11) NOT NULL,
  `pontos_de_cura` int(11) NOT NULL,
  `pontos_de_vida` int(11) NOT NULL,
  `resistencia` double NOT NULL,
  `sabedoria` double NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `xp` int(11) NOT NULL,
  `classe_id` bigint(20) DEFAULT NULL,
  `raca_id` bigint(20) DEFAULT NULL,
  `apelido` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bf1plcophnya1bgoogms7fati` (`classe_id`),
  KEY `FK_241c0raxjxwwkf7ufa04c1xeu` (`raca_id`),
  CONSTRAINT `FK_241c0raxjxwwkf7ufa04c1xeu` FOREIGN KEY (`raca_id`) REFERENCES `raca` (`id`),
  CONSTRAINT `FK_bf1plcophnya1bgoogms7fati` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of personagem
-- ----------------------------
INSERT INTO `personagem` VALUES ('1', '10', '10', '0', '10', 'fribeiro@labrunier.com.br', '10', '0', 'fribeiro@labrunier.com.br', '0', '0', '0', '528', '10', '10', 'labrunier', '0', '12', '7', 'Doidão', 'Fabiano Ribeiro');
INSERT INTO `personagem` VALUES ('2', '7', '7', '0', '7', 'thiago.emanoel@labrunier.com.br', '7', '0', 'thiago.emanoel@labrunier.com.br', '0', '0', '0', '528', '7', '7', 'labrunier', '0', '7', '4', 'Elodiac', 'Thiago Emanoel');
INSERT INTO `personagem` VALUES ('3', '5', '5', '0', '5', 'danillo.xavier@labrunier.com.br', '5', '0', 'danillo.xavier@labrunier.com.br', '0', '0', '0', '528', '5', '5', 'labrunier', '0', '5', '6', 'Manjubex', 'Danillo Xavier');

-- ----------------------------
-- Table structure for raca
-- ----------------------------
DROP TABLE IF EXISTS `raca`;
CREATE TABLE `raca` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of raca
-- ----------------------------
INSERT INTO `raca` VALUES ('1', null, 'Ensino Médio Completo\r\n');
INSERT INTO `raca` VALUES ('2', null, 'Técnico em Informática\r\n');
INSERT INTO `raca` VALUES ('3', null, 'Gestão de T.I\r\n');
INSERT INTO `raca` VALUES ('4', null, 'Análise de Sistemas\r\n');
INSERT INTO `raca` VALUES ('5', null, 'Ciências da Computação\r\n');
INSERT INTO `raca` VALUES ('6', null, 'Engenharia de Produção\r\n');
INSERT INTO `raca` VALUES ('7', null, 'Engenharia de Alimentos\r\n');

-- ----------------------------
-- Table structure for raca_pericia
-- ----------------------------
DROP TABLE IF EXISTS `raca_pericia`;
CREATE TABLE `raca_pericia` (
  `Raca_id` bigint(20) NOT NULL,
  `pericias_basicas_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_sfi4lt7vbod1ckjmcsgxjgmw5` (`pericias_basicas_id`),
  KEY `FK_sfi4lt7vbod1ckjmcsgxjgmw5` (`pericias_basicas_id`),
  KEY `FK_5hmapkbwldgw4vwq16p4m7ux` (`Raca_id`),
  CONSTRAINT `FK_5hmapkbwldgw4vwq16p4m7ux` FOREIGN KEY (`Raca_id`) REFERENCES `raca` (`id`),
  CONSTRAINT `FK_sfi4lt7vbod1ckjmcsgxjgmw5` FOREIGN KEY (`pericias_basicas_id`) REFERENCES `pericia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of raca_pericia
-- ----------------------------

-- ----------------------------
-- Table structure for tesouro
-- ----------------------------
DROP TABLE IF EXISTS `tesouro`;
CREATE TABLE `tesouro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tesouro
-- ----------------------------
INSERT INTO `tesouro` VALUES ('1', 'Ao finalizar a aventura o Mestre da Aventura pode definir um aumento no salário do jogador', 'Aumento Salarial');
INSERT INTO `tesouro` VALUES ('2', 'Ao finalizar a aventura o Mestre da Aventura pode definir uma mudança de cargo ao jogador', 'Mudança de Cargo');
INSERT INTO `tesouro` VALUES ('3', 'Ao finalizar a aventura o Mestre da Aventura pode definir um dia de folga ao jogador', 'Dia de Folga');
INSERT INTO `tesouro` VALUES ('4', 'Ao finalizar a aventura o Mestre da Aventura pode definir horas/turno de folga ao jogador', 'Horas de Folga');
INSERT INTO `tesouro` VALUES ('5', 'Ao finalizar a aventura o Mestre da Aventura pode definir um lanche ao jogador', 'Lanche');

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(255) DEFAULT NULL,
  `celular` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `setor` varchar(255) DEFAULT NULL,
  `telefone` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuario
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
